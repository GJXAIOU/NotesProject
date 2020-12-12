package com.gjxaiou.jdk8.lambdaAndFunctionInterface;


import java.util.function.Supplier;

public class StudentTest {

    public static void main(String[] args) {
        // 获取 Student 对象
        Supplier<Student> supplier = () -> new Student();
        System.out.println(supplier.get().getName());

        System.out.println("-------");

        // 方式二：使用构造方法引用来简化
        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getName());
    }
}
