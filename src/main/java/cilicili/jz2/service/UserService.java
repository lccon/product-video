package cilicili.jz2.service;

import cilicili.jz2.domain.Session;
import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.User;

public interface UserService {
	User findUserById(Integer id);
	
	User getUserByUsername(String username);
	
	User addUser(User user);
	
	User updateUser(User user);

	// LC
	Session getUserByUsernamePassword(String username, String password, Session session);

    User findUserBySessionId(String token);
}
