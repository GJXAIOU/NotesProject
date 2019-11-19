package com.gjxaiou.service.impl;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjxaiou.cache.JedisUtil;
import com.gjxaiou.dao.ShopCategoryDao;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopCategoryExecution;
import com.gjxaiou.entity.ShopCategory;
import com.gjxaiou.enums.OperationStatusEnum;
import com.gjxaiou.enums.ShopCategoryStateEnum;
import com.gjxaiou.exception.ShopCategoryOperationException;
import com.gjxaiou.service.CacheService;
import com.gjxaiou.service.ShopCategoryService;
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-19-10:33
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        // 定义Redis的key前缀
        String key = SC_LIST_KEY;
        // 定义接收对象
        List<ShopCategory> shopCategoryList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 拼接出redis的key
        if (shopCategoryCondition == null) {
            // 若查询条件为空，则列出所有首页大类，即parentId为空的店铺类型
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null
                && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            // 若parentId不为空，则列出该parentId下的所有子类别
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            // 列出所有子类别，不管其属于哪个类都列出
            key = key + "_allsecondlevel";
        }

        // 判断key是否存在
        if (!jedisKeys.exists(key)) {
            // 若不存在，则从数据库中取出数据
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            // 将实体类集合转换为string，存入redis
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            // 若存在，则直接从redis中取出数据
            String jsonString = jedisStrings.get(key);
            // 将String转换为集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }

        return shopCategoryList;
    }

    @Override
    public ShopCategoryExecution addShopCategory(ShopCategory shopCategory,
                                                 ImageHolder shopCategoryImg) {
        // 空值判断
        if (shopCategory == null) {
            return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY);
        } else {
            // 初始化赋值
            shopCategory.setCreateTime(new Date());
            // 店铺图片不为空
            if (shopCategoryImg != null) {
                addShopCategoryImg(shopCategory, shopCategoryImg);
            }
            try {
                int effectNum = shopCategoryDao.insertShopCategory(shopCategory);
                if (effectNum <= 0) {
                    throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo());
                } else {
                    // 删除缓存信息
                    cacheService.removeFromCache(SC_LIST_KEY);
                }
            } catch (Exception e) {
                logger.error(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
                throw new ShopCategoryOperationException(
                        ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
            }
            return new ShopCategoryExecution(OperationStatusEnum.SUCCESS, shopCategory);
        }
    }

    @Override
    public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory,
                                                    ImageHolder shopCategoryImg) {
        // 空值判断
        if (shopCategory != null && shopCategory.getShopCategoryId() != null) {
            // 设置默认更新时间
            shopCategory.setLastEditTime(new Date());
            // 若图不为空且原有图不为空，则先删除原有图并添加
            if (shopCategoryImg != null) {
                // 获取原来商品类别图片并删除
                ShopCategory origShopCategory = shopCategoryDao
                        .selectShopCategoryById(shopCategory.getShopCategoryId());
                if (origShopCategory.getShopCategoryImg() != null) {
                    ImageUtil.deleteFileOrDirectory(origShopCategory.getShopCategoryImg());
                }
                addShopCategoryImg(shopCategory, shopCategoryImg);
            }
            // 更新信息
            try {
                int effectNum = shopCategoryDao.updateShopCategory(shopCategory);
                if (effectNum <= 0) {
                    throw new ShopCategoryOperationException(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo());
                } else {
                    // 删除缓存信息
                    cacheService.removeFromCache(SC_LIST_KEY);
                }
            } catch (Exception e) {
                logger.error(ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
                throw new ShopCategoryOperationException(
                        ShopCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
            }
            return new ShopCategoryExecution(OperationStatusEnum.SUCCESS, shopCategory);
        } else {
            return new ShopCategoryExecution(ShopCategoryStateEnum.NULL_SHOP_CATEGORY);
        }
    }

    @Override
    public ShopCategory getShopCategoryById(long shopCategoryId) {
        return shopCategoryDao.selectShopCategoryById(shopCategoryId);
    }

    /**
     * 添加店铺类别图片
     *
     * @param shopCategory    店铺类别实体类
     * @param shopCategoryImg 店铺类别图片
     */
    private void addShopCategoryImg(ShopCategory shopCategory, ImageHolder shopCategoryImg) {
        // 获取图片存储路径，将图片放在相应店铺类别的文件夹下
        String dest = PathUtil.getShopCategoryImagePath();
        String generateShopCategoryImg = ImageUtil.generateShopCategoryImg(shopCategoryImg, dest);
        shopCategory.setShopCategoryImg(generateShopCategoryImg);
    }


}
