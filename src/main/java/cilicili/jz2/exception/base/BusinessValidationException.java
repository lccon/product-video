package cilicili.jz2.exception.base;

import cilicili.jz2.exception.annotation.Exceptional;
import cilicili.jz2.exception.base.handler.BusinessValidationExceptionHandler;
import cilicili.jz2.exception.constant.ExceptionCode;
import org.springframework.stereotype.Component;

/**
 * 业务规则校验失败异常，该类异常是由于用户误操作导致的，例如“身份证不允许重复”，只需给用户合理的提示，可以由用户自行修正.<br/>
 * 该类异常日志系统不会做任何记录<br/>
 * 该异常的异常码为BE100-01，异常处理类为BusinessValidationExceptionHandler，日志系统将不会记录该异常信息
 * 
 * @date 2019-10-30
 * @author lc
 *
 */
@Component
@Exceptional(isLogging = false, errorCode = ExceptionCode.BUSINESS_VALIDATION, handler = BusinessValidationExceptionHandler.class)
public class BusinessValidationException extends BaseAppRuntimeException {

	public BusinessValidationException() {
		super();
	}

	public BusinessValidationException(String message) {
		super(message);
	}
}
