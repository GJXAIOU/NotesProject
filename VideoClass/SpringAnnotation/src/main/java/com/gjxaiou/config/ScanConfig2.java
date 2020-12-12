//package com.gjxaiou.config;
//
//import com.gjxaiou.service.BookService;
//import org.springframework.context.annotation.ComponentScans;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//
///**
// * @Author GJXAIOU
// * @Date 2020/3/3 18:01
// */
//@Configuration
//// 可以配置单个规则
////@ComponentScan(value = "com.gjxaiou", excludeFilters = {@ComponentScan.Filter(type =FilterType.ANNOTATION, classes = {Service.class, Controller.class})})
//
//// 也可以配置多个规则
//@ComponentScans(value = {
//        @ComponentScan(value = "com.gjxaiou", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
//                        {Controller.class}), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BookService.class)}, useDefaultFilters = false),
//        @ComponentScan(value = "com.gjxaiou", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class})})})
//public class ScanConfig2 {
//}
