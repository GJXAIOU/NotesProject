package com.liuxin.mytest;

/**
 * @author liuxin9619@gmail.com
 * @date 2021/1/24 15:50
 */
public class LeanyTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            /*
             * 测试interrupt函数的作用，使用interrupt函数的作用是轻微的唤醒(进入EntryList)，
             * 将进入sleep或者waite方法的的线程给轻微的唤醒这样。
             * 如果线程中没有以上任何的操作那么对该线程没有任何影响
             */
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int i = 100;
//            while (i > 0){
//                System.out.println("子线程执行完毕");
//                i--;
//            }

            System.out.println("sleep 或者 打印操作执行完毕");

        });

        t.start();
        // 证明一个事情就是主线程执行完毕之后子线程依旧在执行
        System.out.println("主线程执行完毕");
        t.interrupt();
    }
}
