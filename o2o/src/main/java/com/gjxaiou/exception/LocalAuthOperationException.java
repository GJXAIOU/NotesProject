package com.gjxaiou.exception;

/**
 * @Description: 本地账号操作异常
 *
 */
public class LocalAuthOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LocalAuthOperationException(String msg) {
		super(msg);
	}
}