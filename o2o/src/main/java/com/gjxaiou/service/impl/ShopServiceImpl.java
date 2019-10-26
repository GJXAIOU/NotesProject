package com.gjxaiou.service.impl;

import com.gjxaiou.dao.ShopDao;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopExecution;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ShopStateEnum;
import com.gjxaiou.exception.ShopOperationException;
import com.gjxaiou.service.ShopService;
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PageCalculator;
import com.gjxaiou.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author GJXAIOU
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional // 表示该方法需要事务处理
    /**
     * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail)
            throws RuntimeException {
        // 如果店铺为空，抛出异常
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }
        try {
            // 1. 往数据库中增加这条店铺信息，同时给店铺信息赋初始值
            //  0 表示未上架，在审核中：Checked 状态
            shop.setEnableStatus(ShopStateEnum.CHECK.getState());
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail.getImage() != null) {
                    //2 这里需要 shop 对象中的 shopId 创建文件对象，因此需要传入 shop 对象，创建好之后将图片地址更新到 shop 实体类中
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //3， 更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

	/**
	 *  给店铺添加图片
	 * @param shop
	 * @param thumbnail
	 */
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取 shop 图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		// shop 对象属性赋值
		shop.setShopImg(shopImgAddr);
	}


    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

	/**
	 *  更新店铺信息
	 *  	首先更新图片，根据参数 shop 的 Id 获取该店铺是否已经有图片，有就删除再添加新的；
	 *  	然后更新 shop 即可；
	 * @param shop 店铺实体类
	 * @param thumbnail
	 * @return
	 * @throws ShopOperationException
	 */
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        } else {
            // 判断是否需要处理图片
            try {
                if (thumbnail.getImage() != null && !StringUtils.isEmpty(thumbnail.getImageName())) {
                    // 获取该 shop 对象，从而获取其中原来的图片，将其删除之后重新添加
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrDirectory(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail);
                }

                // 更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    // 将修改之后的店铺返回前台
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShopError" + e.getMessage());
            }
        }

    }

	/**
	 * 分页显示查询的店铺信息
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }
}
