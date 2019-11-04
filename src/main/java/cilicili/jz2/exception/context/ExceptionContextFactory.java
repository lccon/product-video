package cilicili.jz2.exception.context;

/**
 * 异常容器创建工厂
 * 
 * @date 2019-10-30
 * @author lc
 */
public class ExceptionContextFactory {
	private static ExceptionContextFactory instance;

	private volatile ExceptionContext exceptionContext;

	private Object exceptionContextLock = new Object();

	private ExceptionContextFactory() {

	}

	public static synchronized ExceptionContextFactory getInstance() {
		if (null == instance) {
			instance = new ExceptionContextFactory();
		}
		return instance;
	}

	public ExceptionContext getExceptionContext() {
		ExceptionContext tempExpContext = exceptionContext;
		if (tempExpContext == null) {
			synchronized (exceptionContextLock) {
				tempExpContext = exceptionContext;
				if (tempExpContext == null)
					exceptionContext = tempExpContext = new ExceptionContext();
			}
		}
		return tempExpContext;
	}
}
