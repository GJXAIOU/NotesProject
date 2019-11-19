package com.gjxaiou.dto;

import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.enums.ProductCategoryStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-11:32
 */
@Getter
@Setter
public class ProductCategoryExecution {

    /**
     * 结果状态值和对应的状态说明
     */
    private int state;
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){

    }

    /**
     * 操作失败时候使用的构造器
     * @param productCategoryStateEnum
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum){
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
    }

    /**
     * 操作成功的时候使用的构造器
     * @param productCategoryStateEnum
     * @param productCategoryList
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,
                                    List<ProductCategory> productCategoryList){
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

}
