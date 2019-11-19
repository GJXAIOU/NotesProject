package com.gjxaiou.dao;

import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-31-19:25
 */
public interface ShopCategoryDao {
    /**
     * 根据店铺类别 ID 获取店铺类型列表
     * 因为是店铺类别有等级，如果参数为空则表示一级店铺类型，反之则是二级等子集店铺类型
     * 	 * 需求：1、首页展示一级目录（即parent_id 为 null的店铺类别）
     * 	 *    2、点进去某个一级目录加载对应目录下的子目录
     * 	 *    3、店铺只能挂在店铺二级类别下
     * 	 *    4、在首页点击某个一级店铺目录 进入店铺展示页面的时候 需要加载对应目录下的子目录
     * @param shopCategory
     * @return
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);

    /**
     *  批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     *  删除指商铺 ID 的商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);
    /**
     * 新增商品分类
     *
     * @param shopCategory
     * @return
     */
    int insertShopCategory(ShopCategory shopCategory);

    /**
     * 修改商品分类
     *
     * @param shopCategory
     * @return
     */
    int updateShopCategory(ShopCategory shopCategory);

    /**
     * 根据Id查询商品分类信息
     *
     * @param shopCategoryId
     * @return
     */
    ShopCategory selectShopCategoryById(long shopCategoryId);
}
