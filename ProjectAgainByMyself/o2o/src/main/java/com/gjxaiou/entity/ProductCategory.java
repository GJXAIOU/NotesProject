package com.gjxaiou.entity;
import	java.awt.Toolkit;

import lombok.*;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private String productCategoryDesc;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
}
