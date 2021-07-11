package com.liuxin.mytest;

import java.util.Scanner;

/**
 * @author liuxin9619@gmail.com
 * @date 2021/2/2 17:07
 */
public class Test3 {
    public static void main(String[] args) {
        int n = 10;
//        n = n / 0.5; // 显然会报错
        n /= 0.5; // 为什么这个不报错，那肯定是做了处理
        q1();
        q2();
        int a = 0;
        int b = 1;
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextInt() + "输入";
        System.out.println(s);
    }

    /**
     * 问题：输出的值是多少？
     */
    public static void q1(){
        boolean x=true;
        boolean y=false;
        short z=42;
        if(y==true)
            if((z++==42) && (y=true))z++;
        if((x=false) || (++z==45)) z++;//注意这里运行了++Z所以结果是43
        System.out.println("z= "+z);
    }

    /**
     * switch-case使用案例相关的内容
     */
    public static void q2(){
        int i = 9;
        switch(i){
            default:
                System.out.println("default");
            case 0:
                System.out.println("0");break;
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
        }
    }
    /**
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
