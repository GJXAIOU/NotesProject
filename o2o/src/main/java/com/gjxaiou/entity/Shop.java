package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-19:28
 */
@Setter
@Getter
@ToString
public class Shop {
    private Long shopId;
    private Long ownerId;
    private Long shopCategoryId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Double longitude;
    private Double latitude;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    /**
     * 店铺状态：-1：表示不可用，0：表示审核中，1：表示可用
     */
    private Integer enableStatus;
    /**
     * advice：表示超级管理员给店家的提醒
     */
    private String advice;

    private PersonInfo owner;
    private Area area;
    private ShopCategory shopCategory;

}
