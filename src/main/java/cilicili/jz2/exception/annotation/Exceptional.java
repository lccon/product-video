package cilicili.jz2.exception.annotation;

import cilicili.jz2.exception.base.handler.DefaultExceptionHandler;
import cilicili.jz2.exception.base.handler.ExceptionHandler;

import java.lang.annotation.*;

/**
 * Description: 注解类
 *
 * @Date:2019/10/30
 * @Author:lc
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Exceptional {
    /**
     * 是否日志记录，默认为true，记录日志
     *
     */
    boolean isLogging() default true;

    /**
     * 异常码
     */
    String errorCode();

    /**
     * 异常级别
     */
    String expLevel() default "error";

    /**
     * 异常处理器,默认处理器为DefaultExceptionHandler
     */
    Class<? extends ExceptionHandler> handler() default DefaultExceptionHandler.class;
}
