package com.lc.service;

import com.lc.domain.Session;
import com.lc.domain.User;

public interface UserService {
	User findUserById(Integer id);
	
	User getUserByUsername(String username);
	
	User addUser(User user);
	
	User updateUser(User user);

	// LC
	Session getUserByUsernamePassword(String username, String password, Session session);

    User findUserBySessionId(String token);
}
