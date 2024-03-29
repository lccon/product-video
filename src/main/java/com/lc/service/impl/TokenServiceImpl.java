package com.lc.service.impl;

import com.lc.dao.TokenMapper;
import com.lc.domain.Token;
import com.lc.exception.base.BusinessValidationException;
import com.lc.exception.base.ServiceValidationException;
import com.lc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service ("tokenService")
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public Token findToken(String token) {
		if (StringUtils.isEmpty(token)) {
			throw new BusinessValidationException("token信息为空！");
		}
		try {
			return tokenMapper.getUserLoginToken(token);
		} catch (Exception e) {
			throw new ServiceValidationException("通过token获取用户信息失败!", e);
		}
	}

	@Override
	public void addToken(Token token) {
		if (token == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		try {
			tokenMapper.insert(token);
		} catch (Exception e) {
			throw new ServiceValidationException("新增用户验证信息失败!", e);
		}
	}

	@Override
	public void updateToken(Token token) {
		if (token == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		try {
			tokenMapper.updateToken(token);
		} catch (Exception e) {
			throw new ServiceValidationException("修改用户验证信息失败!", e);
		}
	}

	@Override
	public void deleteTokens(Integer userId) {
		if (userId == null) {
			throw new BusinessValidationException("参数不能为空！");
		}
		try {
			tokenMapper.deleteTokenByUserId(userId);
		} catch (Exception e) {
			throw new ServiceValidationException("删除用户验证信息失败!", e);
		}
	}
}
