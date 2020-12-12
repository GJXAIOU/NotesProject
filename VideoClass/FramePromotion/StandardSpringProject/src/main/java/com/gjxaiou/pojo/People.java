package com.gjxaiou.pojo;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author GJXAIOU
 * @create 2019-09-22-20:26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class People {
    private Integer id;
    private String name;
    private String gender;
    private Integer score;
    private String tel;
    private List<String> list;
    private Map<String, String> map;
    private Set<String> set;
    private String[] string;
}
