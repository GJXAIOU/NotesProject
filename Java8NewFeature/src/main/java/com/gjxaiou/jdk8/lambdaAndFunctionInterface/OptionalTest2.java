package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.*;

public class OptionalTest2 {

    public static void main(String[] args) {
        /**
         * Part1:单个对象处理
         */
        /**
         * 创建一个包装了 "hello" 字符串的 Optional 容器对象
         * 因为 Optional 的构造方法是私有的，所以只能通过其的几个工厂方法来创建对象，如 of/ofNullable/empty 。
         */
        Optional<String> optional = Optional.ofNullable("hello");
        // 使用 ofNullable() 里面是 null 不会抛出异常
        Optional<String> optional2 = Optional.ofNullable(null);
        optional2.ifPresent(item -> System.out.println(item));
        System.out.println("-----------------");

        // 使用方式一（ isPresent() 不推荐）：
        if (optional.isPresent()) {
            System.out.println("取出方式一（不推荐）：" + optional.get());
        }

        // 推荐的 Optional 使用方式：ifPresent() + Lambda
        optional.ifPresent(item -> System.out.println("推荐的方式：" + item));
        System.out.println("-------");

        // optional 不为空则输出自身，如果没有值值输出后面的备选值
        System.out.println(optional.orElse("world"));
        System.out.println("---------");

        System.out.println(optional.orElseGet(() -> "niHao"));

        /**
         * Part 2：集合处理
         * 需求：取出 company 对象对应的 employee 列表，如果里面不包含任何 employee 信息则返回一个空集合，如果不为空则返回对应列表。
         */
        Employee employee = new Employee("zhangSan");
        Employee employee2 = new Employee("liSi");
        List<Employee> employees = Arrays.asList(employee, employee2);
        Company company = new Company("company1", employees);

        // 情况二：使用该行则返回为 空列表
        company.setEmployees(null);

        // 构造容器
        Optional<Company> optional3 = Optional.ofNullable(company);

        // map() 里面应该是 Function，这里的 Function 对象含义就是输入一个 theCompany,输出 theCompany.getEmployees()
        System.out.println(optional3.map(theCompany -> theCompany.getEmployees()).orElse(Collections.emptyList()));
    }
}