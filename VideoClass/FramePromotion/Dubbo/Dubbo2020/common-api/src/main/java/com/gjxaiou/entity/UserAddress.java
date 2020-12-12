package com.gjxaiou.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author GJXAIOU
 * @Date 2020/12/11 16:49
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddress implements Serializable {
    private Integer id;
    //用户地址
    private String userAddress;
    //用户id
    private String userId;
    //收货人
    private String consignee;
    //电话号码
    private String phoneNum;
    //是否为默认地址    Y-是     N-否
    private String isDefault;
}
