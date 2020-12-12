package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能需求： 对一个按钮 button 注册一个事件监听器，该监听器的作用是当按钮发生某个动作则监听器的特定方法会被调用
 */
public class SwingTest {

    public static void main(String[] args) {
        JFrame jframe = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");

        /**
         * 实现方式一：原始方式：使用匿名内部类
         */
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Pressed!");
                System.out.println("hello world");
                System.out.println("executed");
            }
        });

        /**
         * 实现方式二：Lambda 表达式
         */
        // 这里 ActionEvent 如果不写，Java 编译可以进行类型推断进行推断处理。
        jButton.addActionListener((ActionEvent event) -> {
            System.out.println("Button Pressed!");
            System.out.println("hello world");
            System.out.println("executed");
        });

        // 将按钮 button 添加到 frame
        jframe.add(jButton);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// ActionListener 接口源码
// public interface ActionListener extends EventListener {
//    public void actionPerformed(ActionEvent e);
// }