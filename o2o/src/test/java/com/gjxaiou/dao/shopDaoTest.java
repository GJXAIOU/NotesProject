package com.gjxaiou.dao;

import com.gjxaiou.BaseTest;
import com.gjxaiou.entity.Area;
import com.gjxaiou.entity.PersonInfo;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author GJXAIOU
 * @create 2019-10-17-15:30
 */
public class shopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void insertShopTest(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("Test");
        shop.setShopAddr("Test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");

        // 判断该行记录是否成功添加
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }


    @Test
    public void updateShopTest(){
        Shop shop = new Shop();
        // 目前数据库中仅有一条，作为测试
        shop.setShopId(1L);

        shop.setShopName("测试的店铺");
        shop.setShopDesc("Test修改");
        shop.setShopAddr("Test修改");
        shop.setLastEditTime(new Date());

        // 判断该行记录是否成功添加
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
