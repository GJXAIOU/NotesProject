package com.gjxaiou.dto;

import com.gjxaiou.entity.Product;
import com.gjxaiou.enums.ProductStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-20:04
 */
@Getter
@Setter
public class ProductExecution {
    private int state;
    private String stateInfo;
    /**
     * 商品数量
     */
    private int count;
    /**
     * 操作的 product （增、删、改商品时候使用）
     */
    private Product product;
    /**
     * 获取的 product 列表（查询时候使用）
     */
    private List<Product> productList;
    public ProductExecution(){

    }

    /**
     * 操作失败时候使用的构造器
     * @param productStateEnum
     */
    public ProductExecution(ProductStateEnum productStateEnum){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }

    /**
     * 操作成功使用的构造器（增加、删除、修改操作）
     * @param productStateEnum
     * @param product
     */
    public ProductExecution(ProductStateEnum productStateEnum,Product product){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
        this.product = product;
    }

    public ProductExecution(ProductStateEnum productStateEnum, List<Product> productList){
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
        this.productList = productList;
    }

}
