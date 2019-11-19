package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:22
 */
@Getter
@Setter
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    // 属于哪个商品图片
    private Long productId;
}
