package com.gjxaiou.pojo;

import lombok.*;

/**
 * @author GJXAIOU
 * @create 2019-09-28-20:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LinkMan {
    private Integer lkmId;
    private String lkmName;
    private String lkmGender;
    private String lkmPhone;
    /**
     *  在联系人实体类里面表示所属客户,一个联系人只能属于一个客户
     */
    private Customer customer;
}