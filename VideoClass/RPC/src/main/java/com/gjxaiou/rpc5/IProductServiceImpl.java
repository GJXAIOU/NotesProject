package com.gjxaiou.rpc5;

import com.gjxaiou.IProductService;
import com.gjxaiou.Product;

public class IProductServiceImpl implements IProductService {
    @Override
    public Product findProductByName(String name) {
        return new Product(1,name,1);
    }
}
