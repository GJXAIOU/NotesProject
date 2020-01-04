package com.gjxaiou.class10;

public class MyTest22{
    static{
        System.out.println("MyTest22 initislizer");
    }
    public static void main(String[] args){
        System.out.println(MyTest22.class.getClassLoader());

        System.out.println(MyTest7.class.getClassLoader());
    }
}
/**output:
 * MyTest22 initislizer
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 */