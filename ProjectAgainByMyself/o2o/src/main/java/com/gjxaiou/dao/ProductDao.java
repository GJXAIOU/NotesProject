package com.gjxaiou.dao;

import com.gjxaiou.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-03-20:45
 */
public interface ProductDao {
    /**
     * 根据商品 Id 查询商品
     * @param productId
     * @return
     */
    Product queryProductByProductId(Long productId);

    /**
     *  查询商品列表并分页
     *      可以输入的条件为：商品名（支持模糊），商品状态，店铺 ID、商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询商品总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     *  更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 删除商品之前，将商品类别 Id 置空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);




}
