package com.lc.exception.base.handler;


import com.lc.exception.domain.Result;

/**
 * 业务规则校验异常处理类
 * 
 * @date 2019-10-30
 * @author lc
 */
public class BusinessValidationExceptionHandler implements ExceptionHandler {

	@Override
	public Result handleException(String errorCode, Exception bex, Result result) {
		result.setMessage(bex.getMessage());
		result.setErrorCode(errorCode);
		return result;
	}

}
