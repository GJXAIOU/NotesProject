package com.gjxaiou.dao;

import com.gjxaiou.entity.Shop;

/**
 * @author GJXAIOU
 * @create 2019-10-31-20:13
 */
public interface ShopDao {
    /**
     * 通过 shopId 查询店铺
     * @param shopId
     * @return
     */
    ShopDao queryByShopId(long shopId);

    /**
     * 返回该店铺状态的店铺数
     * @param shopDaoCondition
     * @return
     */
    int queryShopCount(Shop shopDaoCondition);

    /**
     * 新增店铺
     * @param shopDao
     * @return 影响的行数
     */
    int insertShop(Shop shopDao);

}
