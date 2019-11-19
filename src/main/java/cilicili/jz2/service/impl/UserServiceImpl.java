package cilicili.jz2.service.impl;

import cilicili.jz2.dao.UserMapper;
import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.User;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.service.UserService;
import cilicili.jz2.utils.PasswordUtil;
import cilicili.jz2.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Period;

@Service ("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
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
			userMapper.insert(user);
			return user;
		} catch (Exception e) {
			throw new ServiceValidationException("新增用户出错！", e);
		}
	}
	
	@Override
	public User updateUser(User user, String token, String apply) {
		Token tokenApply = TokenUtil.checkToken(apply, TokenUtil.TokenUssage.MODIFY_USER_SETTINGS);
		Token tokenDefault = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
		if (!tokenApply.getUserId().equals(tokenDefault.getUserId())) {
			throw new BusinessValidationException("非本人操作，拒绝授权");
		}
		TokenUtil.destoryToken(token);
		TokenUtil.destoryToken(apply);
		User userCheck = findUserById(tokenApply.getUserId());
		if (userCheck == null || userCheck.getId() == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (user.getUsername() == null) {
			throw new BusinessValidationException("用户名为空");
		}
		if (user.getUsername().length() == 0 || user.getUsername().length() > 20) {
			throw new BusinessValidationException("用户名为空或超过20长度限制");
		}
		if (user.getPassword() == null) {
			throw new BusinessValidationException("密码为空");
		}
		if (user.getPassword().length() == 0 || user.getPassword().length() > 20) {
			throw new BusinessValidationException("密码为空或超过20长度限制");
		}
        try {
			user.setId(userCheck.getId());
			user.setPassword(PasswordUtil.getHashedPassword(user.getPassword()));
			userMapper.updateByPrimaryKeySelective(user);
			return user;
		} catch (Exception e) {
			throw new ServiceValidationException("修改用户信息失败", e);
		}
	}

	@Override
	public Token getUserByUsernamePassword(String username, String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BusinessValidationException("用户名或密码不能为空！");
		}
		password = PasswordUtil.getHashedPassword(password);
		User user = userMapper.getUserByUsernamePassword(username, password);
		if (user == null) {
			throw new BusinessValidationException("用户名或密码错误！");
		}
		try {
			TokenUtil.destoryOldTokens(user.getId());
			Token token = TokenUtil.createToken(user.getId(), TokenUtil.TokenUssage.DEFAULT, TokenUtil.DEFAULT_MAX_COUNT_AUTH, Period.of(0, 1, 0));
			return token;
		} catch (Exception e) {
			throw new ServiceValidationException("获取令牌认证失败！", e);
		}
	}

}
