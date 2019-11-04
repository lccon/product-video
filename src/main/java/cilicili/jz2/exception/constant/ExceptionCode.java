package cilicili.jz2.exception.constant;

/**
 * 异常码类
 * 
 * @date 2019-10-30
 * @author lc
 */
public interface ExceptionCode {
	/**
	 * 业务规则校验异常码,异常信息不被记录
	 */
	String BUSINESS_VALIDATION = "BE100-01";
	/**
	 * 业务规则校验异常码,异常信息被记录
	 */
	String SERVICE_VALIDATION = "SE100-01";
	/**
	 * 系统工具类异常码
	 */
	String SYSTEM_UTIL = "SUE100-01";

	/**
	 * 未知异常码
	 */
	String UNKNOWN_CODE = "UN100-001";

	/**
	 * 错误码
	 */
	String ERROR_CODE = "ERROR100-001";
}
