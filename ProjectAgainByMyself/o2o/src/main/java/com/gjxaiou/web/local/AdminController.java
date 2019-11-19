package com.gjxaiou.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 管理账号
 *
 * @author tyronchen
 * @date 2019年6月7日
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String register() {
		return "/admin/register";
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public String login() {
		return "/admin/login";
	}

	/**
	 * 用户修改密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changePwd")
	public String changePwd() {
		return "/admin/changePwd";
	}

}
