package com.gjxaiou.factory;

import com.gjxaiou.pojo.People;

/**
 * @author GJXAIOU
 * @create 2019-09-23-10:59
 */
public class PeopleFactory {
    public  People newInstance() {
        return new People(4, "张三", "男", 98,"12323232");
    }
}
