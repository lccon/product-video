package cilicili.jz2.exception.context;

import cilicili.jz2.exception.base.BaseAppRuntimeException;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.SystemUtilException;
import cilicili.jz2.exception.base.handler.ExceptionHandler;
import cilicili.jz2.exception.domain.ExceptionDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常容器，在系统启动时存储异常描述和异常处理类相关信息
 * 
 * @date 2019-10-30
 * @author lc
 */
public class ExceptionContext {
	private Map<Class<?>, ExceptionDefinition> exceptionMap;

	private Map<String, ExceptionHandler> handlers = new HashMap<String, ExceptionHandler>();

	ExceptionContext() {
		exceptionMap = new HashMap<Class<?>, ExceptionDefinition>();
	}

	public boolean containsException(Class<?> expClazz) {
		return (exceptionMap.containsKey(expClazz));
	}

	public void setExceptionHandler(Class<?> expClazz,
			Class<? extends ExceptionHandler> handlerClazz) {
		try {
			ExceptionDefinition definition = getRealExceptionDefinition(expClazz);
			if (null == definition) {
				throw new IllegalArgumentException(
						expClazz.getName()
								+ "not in the context, please configure or add it to the context first!!");
			}
			ExceptionHandler handler = handlers.get(handlerClazz.getName());
			if (null == handler) {
				handler = handlerClazz.newInstance();
				handlers.put(handlerClazz.getName(), handler);
			}

			definition.setHandlerName(handlerClazz.getName());
		} catch (Exception ex) {
			throw new SystemUtilException(
					"Add exception handler to context failure!", ex);
		}
	}

	public void setExceptionDefinition(Class<?> expClazz,
			ExceptionDefinition definition) {
		exceptionMap.put(expClazz, definition);
	}

	public ExceptionDefinition getExceptionDefinition(Class<?> expClazz) {
		if (containsException(expClazz)) {
			return exceptionMap.get(expClazz);
		} else {
			return BaseAppRuntimeException.class.isAssignableFrom(expClazz.getSuperclass()) ? this.getExceptionDefinition(expClazz.getSuperclass()) : null;
		}
	}

	public ExceptionDefinition getRealExceptionDefinition(Class<?> expClazz) {
		return exceptionMap.get(expClazz);
	}

	public ExceptionHandler getExceptionHandler(Class<?> expClazz) {
		ExceptionDefinition definition = getExceptionDefinition(expClazz);
		return definition == null ? null : handlers.get(definition
				.getHandlerName());
	}

	public String getErrorCode(Class<?> expClazz) {
		ExceptionDefinition definition = getExceptionDefinition(expClazz);
		return definition == null ? "" : definition.getErrorCode();
	}

}
