package com.gjxaiou;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 21:20
 */
@SpringBootApplication
@ServletComponentScan
public class FilterStart {
    public static void main(String[] args) {
        SpringApplication.run(FilterStart.class, args);
    }
}
