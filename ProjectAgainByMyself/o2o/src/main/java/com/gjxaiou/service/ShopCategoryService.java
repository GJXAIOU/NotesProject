package com.gjxaiou.service;

import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopCategoryExecution;
import com.gjxaiou.entity.ShopCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-19-10:32
 */
public interface ShopCategoryService {
    public final static String SC_LIST_KEY = "shopCategoryList";

    /**
     * 条件获取店铺类别分页列表
     *
     * @param shopCategoryCondition 查询条件
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

    /**
     * 新增商品分类
     *
     * @param shopCategory
     * @param shopCategoryImg
     * @return
     */
    ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder shopCategoryImg);

    /**
     * 修改商品分类
     *
     * @param shopCategory
     * @return
     */
    ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, ImageHolder shopCategoryImg);

    /**
     * 根据Id查询商品分类信息
     *
     * @param shopCategoryId
     * @return
     */
    ShopCategory getShopCategoryById(long shopCategoryId);


}
