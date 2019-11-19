package com.gjxaiou.factory;

import com.gjxaiou.pojo.People;

/**
 * @author GJXAIOU
 * @create 2019-09-06-22:09
 */
public class PeopleFactory {
    public People newInstance(){
        return new People(1,"zhangsan");
    }

}
