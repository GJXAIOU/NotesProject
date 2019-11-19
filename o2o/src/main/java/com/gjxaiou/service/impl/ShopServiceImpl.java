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
import java.util.concurrent.ExecutionException;

/**
 * @author GJXAIOU
 * @create 2019-11-02-10:27
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * 更加店铺 ID 查询店铺
     * @param shopId
     * @return
     */
    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    /**
     * 这里使用 @Transactonal：表示使用注解的方式实现事务处理；使用注解实现控制事务的优点：
     *      1.开发团队达成一致性约定，明确标注事务方法的编程风格；
     *      2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP 请求或者剥离到事务方法外部；
     *      3.不是所有的方法都需要事务，如只有一条修改操作、只读操作时不需要事务控制；
     * @param shop
     * @param thumbnail 图片流
     * @return
     * @throws ShopOperationException
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws RuntimeException {
       // 如果店铺为空，调用错误时候的构造器，返回错误状态
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }
        try {
            // 设置店铺信息部分系统初始值（不用用户指定的），然后插入该店铺信息
                // 店铺状态 0 表示未上架，在审核中，即 checked 状态
            shop.setEnableStatus(ShopStateEnum.CHECK.getState());
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            // 插入并检查店铺是否新增成功
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0){
                // 只有抛出 RuntimeException 事务才能回滚
               throw new ShopOperationException("店铺新建失败");
            }else {
                // 检查店铺对应的图片是否插入成功
                if (thumbnail.getImage() != null) {
                    // 将图片插入店铺
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("添加店铺图片失败" + e.getMessage());
                    }
                    // 更新店铺的图片地址
                    int effectedNum = shopDao.updateShop(shop);
                    if (effectNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("添加店铺失败" + e.getMessage());
        }
        // 调用操作成功的构造函数
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    // TODO: 2019/11/3 这里的 shopImageAddr 到底是什么
    public void addShopImg(Shop shop, ImageHolder thumbnail){
        // 获取图片的路径相对值路径
        String shopImagePath = PathUtil.getShopImagePath(shop.getShopId());
        String shopImageAddr = ImageUtil.generateThumbnail(thumbnail, shopImagePath);
        // 将 shop 的图片赋值给 shop 对象的 shopImg 属性
        shop.setShopImg(shopImageAddr);
    }

    /**
     * 修改店铺信息
     *      首先更新店铺图片：根据参数 shop 中的 shopId 获取原来店铺对应的图片，删除，再添加新的图片信息，最后更新店铺
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null){
            return new  ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }else {
            try {
                // 判断是否需要处理图片,然后删除原来图片并且插入新的图片
                if (thumbnail.getImage() != null && !StringUtils.isEmpty(thumbnail.getImageName())){
                    // TODO: 2019/11/3   这里已经传入了 shop 为什么还要获取 shop
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null){
                        ImageUtil.deleteFileOrDirectory(shop.getShopImg());
                    }
                    addShopImg(shop,thumbnail);
                }

                // 同样，更改店铺之后应该设置系统属性
                shop.setLastEditTime(new Date());
                shop.setEnableStatus(ShopStateEnum.CHECK.getState());

                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0){
                    return  new ShopExecution(ShopStateEnum.INNER_ERROR);
                }else {
                    // 修改成功之后，应该将店铺信息重新返回前台
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS,shop);
                }
            }catch (Exception e){
                throw new ShopOperationException("店铺更新失败" + e.getMessage());
            }
        }
    }

    /**
     * 分页显示店铺查询结果
     * @param shopCondition
     * @param pageIndex 第几页
     * @param pageSize 每页显示几条
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList=  shopDao.queryShopList(shopCondition,rowIndex, pageSize);
        int shopCount = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if (shopList != null){
            shopExecution.setShopList(shopList);
            shopExecution.setCount(shopCount);
        }else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }
}
