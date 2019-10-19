package com.gjxaiou.dao;
import com.gjxaiou.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import	java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-19-9:22
 */
public interface ShopCategoryDao {
    // 返回值为 list，里面装载 shopCategory
    List <ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);

}
