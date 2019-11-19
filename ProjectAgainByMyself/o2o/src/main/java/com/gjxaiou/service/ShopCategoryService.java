package com.gjxaiou.service;

import com.gjxaiou.entity.ShopCategory;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-19-10:32
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
