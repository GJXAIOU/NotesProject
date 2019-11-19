package com.gjxaiou.service.impl;

import com.gjxaiou.dao.ProductCategoryDao;
import com.gjxaiou.dao.ProductDao;
import com.gjxaiou.dto.ProductCategoryExecution;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.enums.ProductCategoryStateEnum;
import com.gjxaiou.exception.ProductCategoryOperationException;
import com.gjxaiou.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-13:58
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    /**
     * 获取该店铺中所有的商品类别
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0){
            try{
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0){
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    /**
     * 删除指定商品属性
     * 应该先将该商品属性的商品的商品属性值为五，然后将该商品属性删除
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        try{
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectedNum <= 0){
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        }catch(Exception e){
            throw  new ProductCategoryOperationException("updateProductCategoryToNull:" + e.getMessage());
        }

        try{
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if (effectedNum <= 0){
                throw new ProductCategoryOperationException("店铺中商品类别更新失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        }
    }
}
