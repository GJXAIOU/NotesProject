package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-10-18:51
 */
@Setter
@Getter
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    /**
     * 0：表示不可用
     * 1：表示可用
     */
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
