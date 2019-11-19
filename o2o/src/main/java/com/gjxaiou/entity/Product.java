package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:22
 */
@Setter
@Getter
public class Product implements Serializable {
    private static final long serialVersionUID = -349433539553804024L;
    private Long productId;
    private String productName;
    private String productDesc;
    // 简略图地址
    private String imgAddr;
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    // 0：表示已经下架，1：在前端展示系统中展示
    private Integer enableStatus;
    private Integer point;

    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;
}
