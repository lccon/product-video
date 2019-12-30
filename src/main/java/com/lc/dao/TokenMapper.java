package com.lc.dao;

import com.lc.domain.Token;

public interface TokenMapper {

    int deleteTokenByUserId(Integer userId);

    int insert(Token record);

    Token getUserLoginToken(String token);

    int updateToken(Token record);
}