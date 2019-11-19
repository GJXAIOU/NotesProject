package com.gjxaiou.exception;

/**
 * @author GJXAIOU
 * @create 2019-11-02-10:19
 */
public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 7923277044845362315L;
    public ShopOperationException(String msg){
        super(msg);
    }
}
