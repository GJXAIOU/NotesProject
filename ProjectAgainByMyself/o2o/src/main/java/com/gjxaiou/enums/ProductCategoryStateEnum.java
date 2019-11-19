package com.gjxaiou.enums;

import lombok.Getter;

/**
 * @author GJXAIOU
 * @create 2019-11-04-11:18
 */
@Getter
public enum ProductCategoryStateEnum {
    SUCCESS(1,"创建成功"), INNER_ERROR(-1001,"操作失败"), EMPTY_LIST(-1002, "添加数小于1");

    private int state;
    private String stateInfo;

    private ProductCategoryStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    private ProductCategoryStateEnum(){

    }

    /**
     * 根据传入的状态值返回枚举类
     * @param index
     * @return
     */
    public static ProductCategoryStateEnum getProductCategoryEnum(int index){
        for (ProductCategoryStateEnum state : values()) {
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
