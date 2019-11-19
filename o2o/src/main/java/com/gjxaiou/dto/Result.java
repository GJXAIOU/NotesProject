package com.gjxaiou.dto;

import lombok.Data;

/**
 * @author GJXAIOU
 * @create 2019-11-04-15:52
 */
@Data
public class Result<T> {
    /**
     * 判断是否成功的标志
     */
    private boolean success;
    /**
     * 成功时返回的数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String errorMeg;

    private int errorCode;

    public Result(){

    }

    /**
     * 错误时候的构造方法
     * @param success
     * @param errorCode
     * @param errorMeg
     */
    public Result(boolean success, int errorCode, String errorMeg){
        this.success = success;
        this.errorCode = errorCode;
        this.errorMeg = errorMeg;
    }

    /**
     * 成功时候的构造方法
     * @param success
     * @param data
     */
    public Result(boolean success, T data){
        this.success = success;
        this.data = data;
    }
}
