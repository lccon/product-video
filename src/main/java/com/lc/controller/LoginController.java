package com.lc.controller;

import com.lc.component.ThreadVariable;
import com.lc.constant.PermissionConstant;
import com.lc.domain.Session;
import com.lc.domain.User;
import com.lc.exception.base.BusinessValidationException;
import com.lc.exception.base.ServiceValidationException;
import com.lc.service.SessionService;
import com.lc.service.impl.UserServiceImpl;
import com.lc.utils.CookieUtil;
import com.lc.utils.IpAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private SessionService sessionService;

	@RequestMapping (value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Session login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		String sessionId = CookieUtil.getSessionId(request);
		CookieUtil.clearSessionsFromCookies(request, response);
		Session session = new Session();
		session.setAccessIp(IpAddressUtil.getIpAddr(request));
		session.setLastUrl(request.getRequestURI());
		session.setSessionId(sessionId);
		Session newSession = userService.getUserByUsernamePassword(username, password, session);
		if (!StringUtils.isEmpty(newSession.getSessionId())) {
			CookieUtil.putSessionIdInCookies(request, response, PermissionConstant.LOGIN_SESSION_ID,
					newSession.getSessionId());
		}
		return newSession;
	}
	
	@RequestMapping (value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Boolean logout(String token, HttpServletRequest request, HttpServletResponse response) {
		//TokenUtil.destoryToken(token);
		CookieUtil.clearSessionsFromCookies(request, response);
		sessionService.deleteSessionBySessionId(token);
		ThreadVariable.setSession(null);
		return true;
	}
	
	@RequestMapping (value = "/now", method = RequestMethod.POST)
	@ResponseBody
	public User now(String token) {
		try {
			User user = userService.findUserBySessionId(token);
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
	public Session apply(String token, String ussage) {
		Session session = ThreadVariable.getSession();
		if(session == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		User user = userService.findUserById(session.getUserId());
		if (user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		return session;
	}
	
}
