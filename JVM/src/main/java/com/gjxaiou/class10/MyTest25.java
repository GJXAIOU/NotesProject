package com.gjxaiou.class10;

public class MyTest25 implements Runnable{
    private Thread thread;
    public MyTest25(){
        // 使用new 就会执行构造方法，就会创建该线程对象
        thread =new Thread(this);
        // 通过start 就会执行下面的run 方法
        thread.start();
    }

    @Override
    public void run(){
        ClassLoader classLoader=this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(classLoader);

        System.out.println("Class:"+classLoader.getClass());
        System.out.println("Parent:"+classLoader.getParent().getClass());
    }

    public static void main(String[] args){
        new MyTest25();
    }
}
/** output:
 * Class:class sun.misc.Launcher$AppClassLoader
 * Parent:class sun.misc.Launcher$ExtClassLoader
 */