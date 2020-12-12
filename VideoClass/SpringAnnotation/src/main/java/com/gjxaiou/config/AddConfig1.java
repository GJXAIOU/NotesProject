//package com.gjxaiou.config;
//
//import com.gjxaiou.bean.Person;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author GJXAIOU
// * @Date 2020/3/3 13:29
// */
//
//// 声明这是一个配置类
//@Configuration
//public class AddConfig1 {
//    // 可以使用 @Bean 给容器中注册一个 Bean，类型为返回值类型，同时方法名作为该 Bean 在容器中的默认 id，如果想指定 id 可以使用 value 属性更改
//    @Bean(value = "person1")
//    public Person person() {
//        return new Person("gjxaiou", 23);
//    }
//}