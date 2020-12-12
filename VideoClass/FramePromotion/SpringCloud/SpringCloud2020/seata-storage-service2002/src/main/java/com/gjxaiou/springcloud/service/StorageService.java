package com.gjxaiou.springcloud.service;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:26
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
