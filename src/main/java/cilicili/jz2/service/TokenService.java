package cilicili.jz2.service;

import cilicili.jz2.domain.Token;

public interface TokenService {
	Token findToken(String token);
	
	void addToken(Token token);
	
	void updateToken(Token token);
	
	void deleteTokens(Integer userId);
}
