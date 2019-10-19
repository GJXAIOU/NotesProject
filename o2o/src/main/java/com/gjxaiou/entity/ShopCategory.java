package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-19:12
 */
@Getter
@Setter
public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
