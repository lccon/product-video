package com.lc.service;

import com.lc.domain.Token;

public interface TokenService {
	Token findToken(String token);
	
	void addToken(Token token);
	
	void updateToken(Token token);
	
	void deleteTokens(Integer userId);
}
