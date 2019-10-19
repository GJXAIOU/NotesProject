package com.gjxaiou.exception;

/**
 * 处理所有商铺操作的异常
 * @author GJXAIOU
 * @create 2019-10-18-11:06
 */
public class ShopOperationException extends RuntimeException{
    public  static final long SERIAL_VERSION_UID = 2361446884822298905L;
    /**
     *  处理异常
     * @param msg：传入的错误信息
     */
    public ShopOperationException(String msg){
        // 将参数传入 RuntimeException 处理
        super(msg);
    }
}
