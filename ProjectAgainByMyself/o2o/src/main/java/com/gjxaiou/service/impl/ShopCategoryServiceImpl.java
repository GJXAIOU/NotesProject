package com.gjxaiou.service.impl;

import com.gjxaiou.dao.ShopCategoryDao;
import com.gjxaiou.entity.ShopCategory;
import com.gjxaiou.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-19-10:33
 */
@Service
public class  ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
