package com.lc.exception.base;

import com.lc.exception.annotation.Exceptional;
import com.lc.exception.constant.ExceptionCode;
import org.springframework.stereotype.Component;

/**
 * 系统工具类异常
 * 
 * @date 2019-10-30
 * @author lc
 */
@Component
@Exceptional(errorCode = ExceptionCode.SYSTEM_UTIL)
public class SystemUtilException extends BaseAppRuntimeException {

	public SystemUtilException() {
		super();
	}

	public SystemUtilException(String message) {
		super(message);
	}

	public SystemUtilException(String message, Throwable cause) {
		super(message, cause);
	}
}