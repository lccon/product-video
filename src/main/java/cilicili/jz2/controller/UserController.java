package cilicili.jz2.controller;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.domain.Session;
import cilicili.jz2.domain.User;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("user")
public class UserController {
    @Autowired
	private UserServiceImpl userService;

	@RequestMapping (value = "/findUsername", method = RequestMethod.POST)
	@ResponseBody
	public User findUserByUsername(String username) {
		return userService.getUserByUsername(username);
	}
	
	@RequestMapping (value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public User addUser(User user) {
        return userService.addUser(user);
	}
	
	@RequestMapping (value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public User updateUser(User user, String token, String apply) {
		Session session = ThreadVariable.getSession();
		if(session == null || session.getUserId() == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		user.setId(session.getUserId());
		return userService.updateUser(user);
	}
}
