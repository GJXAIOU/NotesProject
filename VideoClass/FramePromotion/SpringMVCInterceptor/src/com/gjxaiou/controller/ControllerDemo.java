package com.gjxaiou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author GJXAIOU
 * @create 2019-09-20-19:50
 */
@Controller
public class ControllerDemo {
    @RequestMapping("demo1")
    public String demo1(){
        System.out.println("执行 demo1");
        return "index.jsp";
    }

    @RequestMapping("demo2")
    public ModelAndView demo2(){
        System.out.println("执行 demo2");
        ModelAndView modelAndView = new ModelAndView("/index.jsp").addObject("modelAndView",
                "modelAndView 的值");
        return modelAndView;
    }

    @RequestMapping("demo3")
    public String demo3(Model model){
        System.out.println("执行 demo3");
        model.addAttribute("model", "model 的值");
        return "index.jsp";
    }
}
