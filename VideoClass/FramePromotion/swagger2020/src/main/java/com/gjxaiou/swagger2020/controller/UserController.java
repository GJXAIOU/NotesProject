package com.gjxaiou.swagger2020.controller;

import com.gjxaiou.swagger2020.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author GJXAIOU
 * @Date 2021/1/9 13:45
 */
@RestController
public class UserController {

    @RequestMapping("/getUser")
    public User getUser() {
        return new User();
    }
}
