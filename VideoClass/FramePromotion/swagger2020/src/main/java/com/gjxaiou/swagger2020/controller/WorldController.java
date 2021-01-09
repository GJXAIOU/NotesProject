package com.gjxaiou.swagger2020.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author GJXAIOU
 * @Date 2021/1/9 12:23
 */
@RestController
public class WorldController {
    @RequestMapping("/world")
    public String world() {
        return "world";
    }
}
