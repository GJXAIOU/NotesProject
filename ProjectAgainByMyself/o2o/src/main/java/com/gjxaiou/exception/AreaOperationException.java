package com.gjxaiou.exception;

/**
 * @Description: 区域操作异常
 *
 * @author tyronchen
 * @date 2018年12月15日
 */
public class AreaOperationException extends RuntimeException {

	private static final long serialVersionUID = -1514546946463225L;

	public AreaOperationException(String msg) {
		super(msg);
	}
}
