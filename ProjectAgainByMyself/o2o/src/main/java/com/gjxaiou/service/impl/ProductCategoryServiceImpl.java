package com.gjxaiou.service.impl;

import com.gjxaiou.dto.ProductCategoryExecution;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.exception.ProductCategoryOperationException;
import com.gjxaiou.service.ProductCategoryService;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-13:58
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return null;
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        return null;
    }

    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        return null;
    }
}
