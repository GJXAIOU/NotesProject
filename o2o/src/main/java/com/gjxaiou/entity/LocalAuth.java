package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-18:28
 */
@Getter
@Setter
public class LocalAuth {
    private Long localAuthId;
    private String username;
    private String password;
    private Date createTime;
    private Date lastEditTime;
    private PersonInfo personInfo;
}
