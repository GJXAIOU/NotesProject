package com.gjxaiou.service;

import com.gjxaiou.dto.ProductCategoryExecution;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.exception.ProductCategoryOperationException;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-11:17
 */
public interface ProductCategoryService {

    /**
     * 查询某个店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量新增商品类型
     * @param productCategoryList
     * @return
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 删除指定店铺的商品类别
     * 首先将该类型下所有商品的商品类别 Id 置为空，然后删除该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
