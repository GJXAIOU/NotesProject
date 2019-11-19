package com.gjxaiou.web.frontEnd;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/frontEnd", method={RequestMethod.GET})
public class FrontController {

	/**
	 * 首页路由
	 */
	@RequestMapping(value = "/index")
	private String index() {
		return "frontEnd/index";
	}

	/**
	 * 店铺列表路由
	 */
	@RequestMapping(value = "/shopList")
	private String shopList() {
		return "frontEnd/shopList";
	}

	/**
	 * 店铺详情路由
	 */
	@RequestMapping(value = "/shopDetail")
	private String shopDetail() {
		return "frontEnd/shopDetail";
	}

	/**
	 * 商品详情页路由
	 */
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public String productDetail() {
		return "frontEnd/productDetail";
	}
}

