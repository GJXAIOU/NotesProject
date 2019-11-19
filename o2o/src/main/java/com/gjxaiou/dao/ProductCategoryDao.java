package com.gjxaiou.dao;

import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-03-21:07
 */
public interface ProductCategoryDao {
    /**
     * 通过 shopId 查询该店铺下所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量新增商品列表
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除指定店铺下面的某个商品类型
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);
}
