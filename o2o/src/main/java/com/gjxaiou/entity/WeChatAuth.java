package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-17:25
 */
@Getter
@Setter
public class WeChatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date createTime;
    private PersonInfo personInfo;
}
