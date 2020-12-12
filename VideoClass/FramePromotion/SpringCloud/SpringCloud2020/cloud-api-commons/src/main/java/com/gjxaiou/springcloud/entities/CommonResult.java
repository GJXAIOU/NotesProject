package com.gjxaiou.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于返回给前端的封装结果，通常包括：
 * 1. 返回状态：code，值有 200，404,500...
 * 2. 返回信息：message 包括错误信息，异常信息等等
 * 3. 具体的数据：data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    // 部分场景下不返回任何数据，即 data = null，因此单独提供一个构造方法
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
