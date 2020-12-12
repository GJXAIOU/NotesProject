
 /**
 * FileName:     SpELConfig.java
 * Createdate:   2019-02-17 23:09:25   
 */

package com.lzc.assembly.externals.spel;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzc.assembly.externals.BlankDisc;

/**
 * Description: 使用SpEL的配置  
 * Copyright:   Copyright (c)2019    
 * @author:     LZC  
 * @version:    1.0  
 * @date:   	2019-02-17 23:09:25   
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-17   LZC         1.0         1.0 Version  
 */
@Configuration
public class SpELConfig {

	@Bean
	public BlankDisc blankDisc() {
		return new BlankDisc("学不会", "林俊杰");
	}
	
	/**
	* <p>描述：使用SpEL引用beanId为blankDisc的bean并调用其方法</p>
	* @param title
	* @param artist
	* @return
	* @author LZC
	 */
	@Bean
	@Qualifier("blankDiscSpEL1")
	public BlankDiscSpEL blankDiscSpEL1(@Value("#{blankDisc.getTitle()}")String title, @Value("#{blankDisc.getArtist()}")String artist) {
		return new BlankDiscSpEL(title, artist);
	}
	
	/**
	* <p>描述：使用SpEL读取属性文件内容</p>
	* @param title
	* @param artist
	* @return
	* @author LZC
	 */
	@Bean
	@Qualifier("blankDiscSpEL2")
	public BlankDiscSpEL blankDiscSpEL2(@Value("#{props['disc.title']}")String title, @Value("#{props['disc.artist']}")String artist) {
		return new BlankDiscSpEL(title, artist);
	}

	/**
	* <p>描述：定义bean并加装一个自定义属性文件</p>
	* @return
	* @author LZC
	 */
	@Bean("prop")
	public Properties properties() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("externals/app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	* <p>描述：定义bean并加装多个自定义属性文件</p>
	* @param properties
	* @return
	* @author LZC
	 */
	@Bean("props")
	public PropertiesFactoryBean propertiesFactoryBean(@Qualifier("prop")Properties properties) {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		//加载一个属性文件
		factoryBean.setProperties(properties);
		
		/*
		 * 用来加装多个属性文件，其中属性名称相同的，后加载的会覆盖之前加载的
		 */
//		Properties properties1 = new Properties();
//		Properties properties2 = new Properties();
//		try {
//			properties1.load(this.getClass().getClassLoader().getResourceAsStream("externals/app.properties"));
//			properties2.load(this.getClass().getClassLoader().getResourceAsStream("externals.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		factoryBean.setPropertiesArray(properties1, properties2);
		return factoryBean;
	}
	
	/**
	* <p>描述：使用SpEL读取系统属性。systemProperties是预定义的bean的id，等同于System.getProperties()</p>
	* @param javaVersion
	* @param javaHome
	* @return
	* @author LZC
	 */
	@Bean
	public SystemPropertiesBean systemPropertiesBean(
			@Value("#{systemProperties['java.version']}")String javaVersion,
			@Value("#{systemProperties['java.home']}")String javaHome) {
		return new SystemPropertiesBean(javaVersion, javaHome);
	}
}
