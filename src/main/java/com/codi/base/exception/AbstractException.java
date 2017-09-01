package com.codi.base.exception;

import com.codi.base.domain.BaseResult;

/**
 * 所有异常类的基类
 * */
public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    protected AbstractException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
	public abstract BaseResult toBaseResult();

}
