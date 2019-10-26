package com.gjxaiou.service;

import com.gjxaiou.BaseTest;
import com.gjxaiou.dao.ShopDao;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopExecution;
import com.gjxaiou.entity.Area;
import com.gjxaiou.entity.PersonInfo;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.entity.ShopCategory;
import com.gjxaiou.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author GJXAIOU
 * @create 2019-10-18-11:28
 */
public class ShopServiceTest extends BaseTest {
    // 告诉 spring，在初始化 ShopServiceTest 的时候，将 shopService 的实现类动态的注入到接口中去；
    @Autowired
    private ShopService shopService;

    @Test
    public void modifyShopTest() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("E:\\Program\\Java\\Project\\o2o\\baise.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop,new ImageHolder(inputStream, "baise.jpg"));
        System.out.println("新的图片地址" + shopExecution.getShop().getShopImg());
    }




    @Test
    public void AddShopTest() throws FileNotFoundException {
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
        shop.setShopDesc("Test1");
        shop.setShopAddr("Test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        // 这里报错，需要修改
        File shopImg = new File("E:\\Program\\Java\\Project\\o2o\\xiaohuangren.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop, new ImageHolder(inputStream,
                shopImg.getName()));
        // 理想值和实际值是否相等
       assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

}
