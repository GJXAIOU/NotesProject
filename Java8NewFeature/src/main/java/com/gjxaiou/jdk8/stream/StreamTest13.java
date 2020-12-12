package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest13 {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        // 按照姓名进行分组
        Map<String, List<Student>> map = students.stream().
                collect(Collectors.groupingBy(Student::getName));
        // 按照分数进行分组
        Map<Integer, List<Student>> map2 = students.stream().
                collect(Collectors.groupingBy(Student::getScore));

        // 分组之后，每个分组有多少个元素
        Map<String, Long> map3 = students.stream().
                collect(Collectors.groupingBy(Student::getName, Collectors.counting()));

        // 分组之后，每个分组的分数平均值
        Map<String, Double> map4 = students.stream().
                collect(Collectors.groupingBy(Student::getName,
                        Collectors.averagingDouble(Student::getScore)));

        // 分区，只有两组（符合和不符合）
        Map<Boolean, List<Student>> map5 = students.stream().
                collect(Collectors.partitioningBy(student -> student.getScore() >= 90));

        System.out.println(map);
    }
}
