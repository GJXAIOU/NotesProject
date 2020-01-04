package com.gjxaiou.memory;

/**
 * 虚拟机栈溢出测试(使用递归)
 * @Author GJXAIOU
 * @Date 2019/12/11 16:53
 */

public class MyTest2 {
    // 查看一共递归了多少层
    private int length;
    public int getLength() {
        return length;
    }

    public void test() throws InterruptedException {
        length++;
        Thread.sleep(300);
        test();
    }

    public static void main(String[] args) {
        //测试调整虚拟机栈内存大小为：  -Xss160k，此处除了可以使用JVisuale监控程序运行状况外还可以使用jconsole
        MyTest2 myTest2 = new MyTest2();
        try {
            myTest2.test();
        } catch (Throwable e) {
            //打印最终的最大栈深度为：2581
            System.out.println(myTest2.getLength());
            e.printStackTrace();
        }
    }
}
