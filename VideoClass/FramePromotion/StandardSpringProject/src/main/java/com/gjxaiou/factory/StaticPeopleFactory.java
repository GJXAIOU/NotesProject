package com.gjxaiou.factory;

import com.gjxaiou.pojo.People;

/**
 * @author GJXAIOU
 * @create 2019-09-23-11:13
 */
public class StaticPeopleFactory {
    public static People newInstance(){
        return new People(5, "李四", "女", 98,"12323232");
    }
}
