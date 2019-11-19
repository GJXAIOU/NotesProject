package com.gjxaiou.service;
import	java.awt.Image;

import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopExecution;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.exception.ShopOperationException;

/**
 * @author GJXAIOU
 * @create 2019-10-31-22:03
 */
public interface ShopService {
    /**
     * 增加店铺
     * @param shop
     * @param thumbnail 图片流
     * @return 包括店铺实体类信息和相关状态值
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws RuntimeException;

    /**
     * 修改店铺
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 根据店铺 Id 获取店铺所有的信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 根据店铺状态（shopCondition）分页查询店铺
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
