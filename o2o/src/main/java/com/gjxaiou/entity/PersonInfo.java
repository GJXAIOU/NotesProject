package com.gjxaiou.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:22
 */
@Setter
@Getter
@ToString
public class PersonInfo {
    private Long userId;
    private Long localAuthId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    private Integer enableStatus;
    /**
     *  1：表示顾客；
     *  2：表示店家;
     *  3：表示超级管理员；
     */
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;

}
