package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-15:44
 */
@Getter
@Setter
@ToString
public class Area {
    private Integer areaId;
    private String areaName;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
}
