package com.lzc.invoker.bean;

import java.io.Serializable;

public class Spitter implements Serializable {

	private static final long serialVersionUID = 5188036875715711373L;
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
