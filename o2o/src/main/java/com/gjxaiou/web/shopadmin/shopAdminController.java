package com.gjxaiou.web.shopadmin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author GJXAIOU
 * @create 2019-10-18-20:35
 */
@Controller
@RequestMapping(value="/shopAdmin", method ={RequestMethod.GET})
public class shopAdminController {

    /**
     * 转发至店铺注册/编辑页面
     *
     * @return
     */
    // 返回一个字符串，告诉转发至哪一个 HTML 页面
    @RequestMapping(value = "/shopOperation")
    public String shopOperation(){
        // 因为在 spring-web 中已经配置了前后缀
        return "shop/shopOperation";
    }

    /**
     * 转发至店铺列表页面
     *
     * @return
     */
    @RequestMapping(value = "/shopList")
    public String shopList() {
        return "shop/shopList";
    }

    /**
     * 转发至店铺管理页面
     *
     * @return
     */
    @RequestMapping(value = "/shopManagement")
    public String shopManagement() {
        return "shop/shopManagement";
    }

    /**
     * 转发至商品类别管理页面
     *
     * @return
     */
    @RequestMapping(value = "/productCategoryManagement")
    public String productCategoryManagement() {
        return "shop/productCategoryManagement";
    }

    /**
     * 转发至商品添加/编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/productOperation")
    public String productOperation() {
        return "shop/productOperation";
    }

    /**
     * 转发至商品添加/编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/productManagement")
    public String productManagement() {
        return "shop/productManagement";
    }
}
