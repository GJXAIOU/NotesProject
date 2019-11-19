package com.gjxaiou.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author GJXAIOU
 * @create 2019-10-29-22:20
 */
@Setter
@Getter
@ToString
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    /** enableStatus 状态值：
     * 0：表示不可用
     * 1：表示可用
     */
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
