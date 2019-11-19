package com.gjxaiou.service;

import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ProductExecution;
import com.gjxaiou.entity.Product;
import com.gjxaiou.exception.ProductOperationException;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-20:03
 */

public interface ProductService {

    /**
     * 通过商品id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊）、商品状态、店铺id、商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 添加商品信息和对应的图片处理
     * @param product
     * @param thumbnail
     * @param productImageHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> productImageHolderList) throws ProductOperationException;

    /**
     * 修改图片信息和对应的图片处理
     * @param product
     * @param thumbnail
     * @param productImageHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product,ImageHolder thumbnail,
                                   List<ImageHolder> productImageHolderList) throws ProductOperationException;

}
