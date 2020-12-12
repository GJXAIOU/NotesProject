package com.gjxaiou.factory;

import com.gjxaiou.pojo.People;

/**
 * @author GJXAIOU
 * @create 2019-09-06-22:19
 */
public class StaticPeopleFactory {
    public static People newInstance() {
        return new People(1,"lisi");
    }
}
