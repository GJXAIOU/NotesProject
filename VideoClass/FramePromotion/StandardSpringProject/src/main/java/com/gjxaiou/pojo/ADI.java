package com.gjxaiou.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author GJXAIOU
 * @create 2019-09-23-13:53
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ADI {
    private Integer Id;
    private String name;
    private BDI bdi;
}
