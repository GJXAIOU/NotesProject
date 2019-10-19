package com.gjxaiou.web.shopadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author GJXAIOU
 * @create 2019-10-18-20:35
 */
@Controller
@RequestMapping(value="shopadmin", method ={RequestMethod.GET})
public class shopAdminController {
    // 返回一个字符串，告诉转发至哪一个 HTML 页面
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        // 因为在 spring-web 中已经配置了前后缀
        return "/shop/shopOperation";
    }

}
