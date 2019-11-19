package main.Demo;

/**
 * @author GJXAIOU
 * @create 2019-09-17-21:23
 */

import org.junit.Test;

public  class Demo {
    @Test
    public void test(){
       Parent parent =  new Child();
       System.out.println(parent.getClass());



    }
}

class Parent{

}

class Child extends Parent {

}
