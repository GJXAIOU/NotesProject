package com.gjxaiou.exception;

/**
 * @Description: 店铺类别操作异常
 *
 */
public class ShopCategoryOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShopCategoryOperationException(String msg) {
		super(msg);
	}
}
