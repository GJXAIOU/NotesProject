package com.lzc.invoker.bean;

import java.io.Serializable;

public class Spitter implements Serializable {

	/*
	 * serialVersionUID 要一致，否则报错
	 * 成员属性名称要一致，否则null值
	 * 成员方法（getter）名称可以不一样，但不建议
	 * 
	 * 至于Spitter和SpitterService重复定义的问题，可以抽取成公共项目，以pom的方式作为依赖引入项目
	 */
	
//	private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = 5188036875715711373L;//要一致
	
	private Long id;
	private String username;
	private String password;

	public Spitter(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
