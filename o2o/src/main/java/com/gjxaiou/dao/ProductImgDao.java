package com.gjxaiou.dao;

import com.gjxaiou.entity.ProductImg;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-20:56
 */
public interface ProductImgDao {
    /**
     * 根据店铺 Id 获取该店铺下的所有详情图片列表
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情页图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 根据店铺 Id 删除该店铺下所有详情图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
