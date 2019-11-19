package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:23
 */
@Getter
@Setter
@ToString
public class WeChatAuth {
    private Long weChatAuthId;
    private String openId;
    private Date createTime;
    private PersonInfo personInfo;
}
