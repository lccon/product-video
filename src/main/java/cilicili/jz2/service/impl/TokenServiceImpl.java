package cilicili.jz2.service.impl;

import cilicili.jz2.dao.TokenMapper;
import cilicili.jz2.domain.Token;
import cilicili.jz2.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("tokenService")
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public Token findToken(String token) {
		return tokenMapper.getUserLoginToken(token);
	}

	@Override
	public void addToken(Token token) {
		tokenMapper.insert(token);
	}

	@Override
	public void updateToken(Token token) {
		tokenMapper.updateToken(token);
	}

	@Override
	public void deleteTokens(Integer userId) {
		tokenMapper.deleteTokenByUserId(userId);
	}
}
