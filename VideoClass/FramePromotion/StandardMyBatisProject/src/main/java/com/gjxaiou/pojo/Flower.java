package com.gjxaiou.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author GJXAIOU
 * @create 2019-09-22-10:51
 */
@Setter
@Getter
@ToString
public class Flower {
    private Integer id;
    private String name;
    private String location;
    private Integer height;

    public Flower(){

    }
    public Flower(Integer id, String name, String location, Integer height){
        this.id = id;
        this.name = name;
        this.location = location;
        this.height = height;
    }
}
