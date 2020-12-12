package com.gjxaiou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 22:04
 */
@SpringBootApplication
@ServletComponentScan
public class ListenerStart {
    public static void main(String[] args) {
        SpringApplication.run(ListenerStart.class, args);
    }
}
