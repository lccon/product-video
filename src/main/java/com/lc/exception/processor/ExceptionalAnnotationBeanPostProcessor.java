package com.lc.exception.processor;

import com.lc.exception.annotation.Exceptional;
import com.lc.exception.base.BaseAppRuntimeException;
import com.lc.exception.context.ExceptionContext;
import com.lc.exception.context.ExceptionContextFactory;
import com.lc.exception.domain.ExceptionDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 异常bean后置处理器，在spring容器启动时自动将异常码、异常、异常处理器三者关联起来
 * 
 * @date 2019-10-30
 * @author lc
 *
 */
public class ExceptionalAnnotationBeanPostProcessor implements
        BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
        if (bean instanceof BaseAppRuntimeException) {
            Exceptional exceptional = bean.getClass().getAnnotation(
                    Exceptional.class);
            if (null != exceptional) {
                ExceptionContext ctx = ExceptionContextFactory.getInstance()
                        .getExceptionContext();
                if (!ctx.containsException(bean.getClass())) {
                    ExceptionDefinition expDefinition = new ExceptionDefinition(
                            exceptional.errorCode(), exceptional.expLevel(),
                            exceptional.isLogging());
                    ctx.setExceptionDefinition(bean.getClass(), expDefinition);
                }
                ctx.setExceptionHandler(bean.getClass(), exceptional.handler());
                return null;
            }
        }
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
