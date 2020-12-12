package com.gjxaiou.classloader;

public class MyTest25 implements Runnable {
    private Thread thread;

    public MyTest25() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);

        System.out.println("Class: " + classLoader.getClass());
        // 运行至上一行
        // Class: class sun.misc.Launcher$AppClassLoader
        System.out.println("Parent: " + classLoader.getParent().getClass());
        // 运行至上一行
        // Parent: class sun.misc.Launcher$ExtClassLoader
    }

    public static void main(String[] args) {
        new MyTest25();
    }
}
