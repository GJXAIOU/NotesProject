package com.gjxaiou.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// @Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    // 因为数据库中 id 为 bigint
    private Long id;
    private String serial;
}
