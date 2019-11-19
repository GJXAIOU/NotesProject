package com.gjxaiou.service.impl;

import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ProductExecution;
import com.gjxaiou.entity.Product;
import com.gjxaiou.exception.ProductOperationException;
import com.gjxaiou.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-20:53
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    pricate 



    @Override
    public Product getProductById(long productId) {

    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                       List<ImageHolder> productImageHolderList) throws ProductOperationException {
        return null;
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImageHolderList) throws ProductOperationException {
        return null;
    }
}
