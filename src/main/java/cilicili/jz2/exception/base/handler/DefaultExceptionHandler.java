package cilicili.jz2.exception.base.handler;

import cilicili.jz2.exception.domain.Result;

/**
 * 默认异常处理类，如果自定义的异常类继承自BaseAppException或者BaseAppRuntimeException并且异常处理类没有配置，
 * 则使用该类 。e.g. @Exceptional(errorCode = "SE100-01") public class SystemException
 * extends BaseAppRuntimeException
 * 
 * @date 2019-10-30
 * @version 1.0.0
 * @author lc
 */
public class DefaultExceptionHandler implements ExceptionHandler {
	public Result handleException(String errorCode, Exception bex, Result result) {
		result.setMessage("异常代码:[" + errorCode + "] " + bex.getMessage());
		result.setErrorCode(errorCode);
		return result;
	}

}
