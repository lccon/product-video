package cilicili.jz2.dao;

import cilicili.jz2.domain.Token;

public interface TokenMapper {

    int deleteTokenByUserId(Integer userId);

    int insert(Token record);

    Token getUserLoginToken(String token);

    int updateToken(Token record);
}