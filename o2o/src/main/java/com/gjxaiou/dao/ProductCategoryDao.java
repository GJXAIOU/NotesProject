package com.gjxaiou.dao;


import com.gjxaiou.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

	/**
	 * 通过店铺 id 查询店铺商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 批量新增商品类别
	 * 
	 * @param productCategoryList
	 * @return 影响的行数
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 删除指定商铺的商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

}
