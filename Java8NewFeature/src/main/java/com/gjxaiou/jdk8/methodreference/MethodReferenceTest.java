package com.gjxaiou.jdk8.methodreference;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceTest {

    public String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }

    public String getString2(String str, Function<String, String> function) {
        return function.apply(str);
    }


    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        List<Student> students = Arrays.asList(student1, student2, student3);

//        /**
//         * 方式一：类::静态方法
//         */
//        // 方式一：默认方式：Lambda 表达式， list 自身就有 sort 方法(默认方法)
//        students.sort((studentParam1, studentParam2) ->
//                Student.compareStudentByScore(studentParam1, studentParam2));
//        students.forEach(student -> System.out.println(student.getScore() + " " + student.getName()));
//        System.out.println("-------");
//
//        // 方式二：方法引用，正好有方法与上面 Lambda 表达式含义相同
//        students.sort(Student::compareStudentByScore);
//        students.forEach(student -> System.out.println(student.getScore() + " " + student.getName()));
//        System.out.println("-------");
//
//        // 使用方式同上
//        students.sort(Student::compareStudentByName);
//        students.forEach(student -> System.out.println(student.getName() + " " + student.getScore()));
//        System.out.println("-------");
//
//        /**
//         * 方式二：引用名（对象名）::实例方法名
//         */
//        StudentComparator studentComparator = new StudentComparator();
//
//        // Lambda 表达式
//        students.sort((studentParam1, studentParam2) ->
//                studentComparator.compareStudentByScore(studentParam1, studentParam2));
//        students.forEach(student -> System.out.println(student.getScore() + " " + student.getName()));
//        System.out.println("-------");
//
//        // 方法引用
//        students.sort(studentComparator::compareStudentByScore);
//        students.forEach(student -> System.out.println(student.getScore() + " " + student.getName()));
//        System.out.println("-------");
//
//        // 使用方式如上
//        students.sort(studentComparator::compareStudentByName);
//        students.forEach(student -> System.out.println(student.getName() + " " + student.getScore()));
//        System.out.println("-------");


        /**
         * 方式三：类名::实例方法名
         */
        // compareByScore() 方法是 sort() 方法里面接收的 Lambda 表达式的第一个参数调用的。如果接收多个参数，则除了第一个后面的所有参数都作为
        // compareByScore 的方法参数传递进去。所以调用该方法的是待排序的两个 Student 对象的第一个，第二个对象作为参数传入该方法。
        students.sort(Student::compareByScore);
        students.forEach(student -> System.out.println(student.getScore() + " " + student.getName()));
        System.out.println("-----------");
        students.sort(Student::compareByName);
        students.forEach(student -> System.out.println(student.getName() + " " + student.getScore()));
        System.out.println("-----------");

        // 第二个示例：使用 JDK 自带的排序方法进行排序
        List<String> cities = Arrays.asList("qingdao", "chongqing", "tianjin", "beijing");
        // lambda 表达式
        Collections.sort(cities, (city1, city2) -> city1.compareToIgnoreCase(city2));
        cities.forEach(city -> System.out.println(city));
        System.out.println("-----------");

        Collections.sort(cities, String::compareToIgnoreCase);
        cities.forEach(System.out::println);
        System.out.println("-----------");

        /**
         * 方式四(构造方法引用)：类名::new
         */
        MethodReferenceTest methodReferenceTest = new MethodReferenceTest();
        System.out.println(methodReferenceTest.getString(String::new));
        System.out.println(methodReferenceTest.getString2("hello", String::new));

    }
}
