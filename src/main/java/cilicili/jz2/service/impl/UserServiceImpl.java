package cilicili.jz2.service.impl;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.dao.UserMapper;
import cilicili.jz2.domain.Session;
import cilicili.jz2.domain.User;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.redis.template.RedisTemplate;
import cilicili.jz2.service.SessionService;
import cilicili.jz2.service.UserService;
import cilicili.jz2.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.UUID;

@Service ("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public User findUserById(Integer id) {
		try {
			return userMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceValidationException("查询用户出错!", e);
		}
	}
	
	@Override
	public User getUserByUsername(String username) {
		if(StringUtils.isEmpty(username)) {
			throw new BusinessValidationException("用户名不能为空！");
		}
		try {
			User user = userMapper.getUserByUsername(username);
			user.setPassword(null);
			return user;
		} catch (Exception e) {
			throw new ServiceValidationException("获取用户失败！", e);
		}
	}
	
	@Override
	public User addUser(User user) {
		if (StringUtils.isEmpty(user.getUsername())) {
			throw new BusinessValidationException("用户名不能为空！");
		}
		if (user.getUsername().length() == 0 || user.getUsername().length() >= 20) {
			throw new BusinessValidationException("用户名为空或超过20长度限制");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new BusinessValidationException("密码不能为空！");
		}
		if (user.getPassword().length() == 0 || user.getPassword().length() >= 20) {
			throw new BusinessValidationException("密码为空或超过20长度限制");
		}
		try {
			user.setId(null);
			user.setIdentity("普通会员");
			user.setPassword(PasswordUtil.getHashedPassword(user.getPassword()));
			userMapper.addUser(user);
			return user;
		} catch (Exception e) {
			throw new ServiceValidationException("新增用户出错！", e);
		}
	}
	
	@Override
	public User updateUser(User user) {
		User userCheck = findUserById(user.getId());
		if (userCheck == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			throw new BusinessValidationException("用户名为空");
		}
		if (user.getUsername().length() == 0 || user.getUsername().length() > 20) {
			throw new BusinessValidationException("用户名为空或超过20长度限制");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new BusinessValidationException("密码为空");
		}
		if (user.getPassword().length() == 0 || user.getPassword().length() > 20) {
			throw new BusinessValidationException("密码为空或超过20长度限制");
		}
        try {
			user.setPassword(PasswordUtil.getHashedPassword(user.getPassword()));
			userMapper.updateByPrimaryKeySelective(user);
			return user;
		} catch (Exception e) {
			throw new ServiceValidationException("修改用户信息失败", e);
		}
	}

	@Override
	public Session getUserByUsernamePassword(String username, String password, Session session) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BusinessValidationException("用户名或密码不能为空！");
		}
		password = PasswordUtil.getHashedPassword(password);
		User user = userMapper.getUserByUsernamePassword(username, password);
		if (user == null) {
			throw new BusinessValidationException("用户名或密码错误！");
		}
		try {
			// 删除登录过的session
			sessionService.deleteSessionBySessionId(session.getSessionId());
			Session newSession = proccessLoginSuccess(user, session);
			/*TokenUtil.destoryOldTokens(user.getId());
			Token token = TokenUtil.createToken(user.getId(), TokenUtil.TokenUssage.DEFAULT, TokenUtil.DEFAULT_MAX_COUNT_AUTH, Period.of(0, 1, 0));*/
			return newSession;
		} catch (Exception e) {
			throw new ServiceValidationException("获取令牌认证失败！", e);
		}
	}

	@Override
	public User findUserBySessionId(String sessionId) {
		Session session = sessionService.getSessionBySessionId(sessionId);
		if(session == null) {
			return new User();
		}
		return findUserById(session.getUserId());
	}

	private Session proccessLoginSuccess(User user, Session session) {
		String sid = UUID.randomUUID().toString();
		Session newSession = new Session();
		newSession.setLoginIp(session.getAccessIp());
		newSession.setUserName(user.getUsername());
		newSession.setAccessTime(Calendar.getInstance().getTime());
		newSession.setSessionId(sid);
		newSession.setIsLogin(true);
		newSession.setLastUrl(session.getLastUrl());
		newSession.setAccessIp(session.getSessionId());
		newSession.setUserId(user.getId());
		newSession = sessionService.addSession(newSession);
		ThreadVariable.setSession(newSession);
		redisTemplate.set(newSession.getSessionId(), newSession);
		return newSession;
	}

}
