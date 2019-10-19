package com.gjxaiou.dao;

import com.gjxaiou.entity.Shop;

/**
 * @author GJXAIOU
 * @create 2019-10-17-15:09
 */
public interface ShopDao {
    /**
     *  新增店铺
     * @param shop
     * @return 影响的行数
     */
    int insertShop(Shop shop);


    /**
     *  更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
