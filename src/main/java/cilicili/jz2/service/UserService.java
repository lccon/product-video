package cilicili.jz2.service;

import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.User;

public interface UserService {
	User findUserById(Integer id);
	
	User getUserByUsername(String username);
	
	User addUser(User user);
	
	User updateUser(User user, String token, String apply);

	// LC
	Token getUserByUsernamePassword(String username, String password);

}
