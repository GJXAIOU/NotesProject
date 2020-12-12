package com.gjxaiou.pojo;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author GJXAIOU
 * @create 2019-09-28-20:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private Integer cid;
    private String custName;
    private String custLevel;
    private String custSource;
    private String custPhone;
    private String custMobile;
    /**
     * 在客户实体类里面表示多个联系人，一个客户有多个联系人
     * hibernate要求使用集合表示多的数据，使用set集合
     */
    private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
}