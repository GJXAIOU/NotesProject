package com.gjxaiou.classloader;

/**
 * @Author GJXAIOU
 * @Date 2020/2/13 10:14
 */
public class MyTest5_4 {
    public static void main(String[] args) {
        System.out.println(MyChild5_4.thread);
    }
}

interface MyParent5_4 {
    public static Thread thread = new Thread(){
        {
            System.out.println(" MParent5 invoke");
        }
    };
}

interface MyChild5_4 extends MyParent5_4 {
    public static Thread thread = new Thread(){
        {
            System.out.println(" MyChild5 invoke");
        }
    };
}
/**output:
 *  MyChild5 invoke
 *  Thread[Thread-0,5,main]
 */
