package cilicili.jz2.controller;

import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.User;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.service.impl.UserServiceImpl;
import cilicili.jz2.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping (value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Token login(String username, String password) {
		return userService.getUserByUsernamePassword(username, password);
	}
	
	@RequestMapping (value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Boolean logout(String token) {
		TokenUtil.destoryToken(token);
		return true;
	}
	
	@RequestMapping (value = "/now", method = RequestMethod.POST)
	@ResponseBody
	public User now(String token) {
		try {
			Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
			User user = userService.findUserById(tokenCheck.getUserId());
			user.setPassword(null);
			return user;
		} catch (BusinessValidationException e) {
			throw new BusinessValidationException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceValidationException("通过令牌获取用户信息失败！", e);
		}
	}
	
	@RequestMapping (value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public Token apply(String token, String ussage) {
		Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
		User user = userService.findUserById(tokenCheck.getUserId());
		if (user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (ussage.equals(TokenUtil.TokenUssage.UPLOAD_FILE) || ussage.equals(TokenUtil.TokenUssage.UPDATE_VIDEO_INFO) || ussage.equals(TokenUtil.TokenUssage.MODIFY_USER_SETTINGS)) {
			Token newToken = TokenUtil.createToken(user.getId(), ussage, 1, Period.of(0, 0, 1));
			return newToken;
		} else {
			throw new BusinessValidationException("非法获取权限");
		}
	}
	
}
