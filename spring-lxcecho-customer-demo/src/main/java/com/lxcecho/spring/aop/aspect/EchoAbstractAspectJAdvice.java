package com.lxcecho.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public abstract class EchoAbstractAspectJAdvice implements EchoAdvice {

	private Object aspect;
	private Method adviceMethod;
	private String throwName;

//    public GPAdvice(Object aspect, Method adviceMethod) {
//        this.aspect = aspect;
//        this.adviceMethod = adviceMethod;
//    }

	public EchoAbstractAspectJAdvice(Object aspect, Method adviceMethod) {
		this.adviceMethod = adviceMethod;
		this.aspect = aspect;
	}

	protected Object invokeAdviceMethod(EchoJoinPoint jp, Object returnValue, Throwable t) throws Throwable {

		//LogAspect.before(),LogAspect.after()  ...
		Class<?>[] paramTypes = this.adviceMethod.getParameterTypes();
		if (null == paramTypes || paramTypes.length == 0) {
			return this.adviceMethod.invoke(this.aspect);
		} else {
			Object[] args = new Object[paramTypes.length];
			for (int i = 0; i < paramTypes.length; i++) {
				if (paramTypes[i] == EchoJoinPoint.class) {
					args[i] = jp;
				} else if (paramTypes[i] == Throwable.class) {
					args[i] = t;
				} else if (paramTypes[i] == Object.class) {
					args[i] = returnValue;
				}
			}
			return this.adviceMethod.invoke(aspect, args);
		}

	}
}
