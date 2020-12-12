
 /**
 * FileName:     RESTClient.java
 * Copyright (c) 2019 lzc.All Rights Reserved.
 */

package client;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import spittr.Spittle;

/**
 * Description: REST客户端  
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-16 18:04:50  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-16   lzc         1.0         1.0 Version  
 */

public class RESTClient {
	
	/*
	 * 测试前先启动web服务，优先执行post4XXX方法，填充好测试数据
	 */
	
	private RestTemplate template = new RestTemplate();
	
	@Test
	public void get4Object() {
		Spittle spittle = template.getForObject(urlHasParam(), Spittle.class, 1L);
		assertEquals(createSpittle(1), spittle);
	}
	
	@Test
	public void get4Entity() {
		ResponseEntity<Spittle> response = template.getForEntity(urlHasParam(),
				Spittle.class, 1L);
		System.out.println(response.getStatusCode());
		assertEquals(createSpittle(1), response.getBody());
	}
	
	@Test
	public void post4Object() {
		Spittle spittle = template.postForObject(url(),
				createSpittle(1), Spittle.class);
		System.out.println(spittle.getId());
	}
	
	@Test
	public void post4Entity() {
		ResponseEntity<Spittle> response = template.postForEntity(url(),
				createSpittle(2), Spittle.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders().getLocation());
	}

	@Test
	public void post4Location() {
		URI uri = template.postForLocation(url(), createSpittle(3));
		System.out.println(uri);
	}
	
	@Test
	public void put() {
		template.put(urlHasParam(), updatedSpittle(2), 2L);
	}
	
	@Test
	public void delete() {
		template.delete(urlHasParam(), 1L);
	}
	
	//返回包含特定资源URL的头信息
	@Test
	public void head4Headers() {
		HttpHeaders httpHeaders = template.headForHeaders(url());
		System.out.println(httpHeaders.getLocation());
		System.out.println(httpHeaders.getOrigin());
		Set<HttpMethod> httpMethods = httpHeaders.getAllow();
		System.out.println(httpMethods.size());
		for(HttpMethod hm : httpMethods) {
			System.out.println(hm);
		}
	}
	
	//返回特定URL的Allow头信息
	@Test
	public void options4Allow() {
		Set<HttpMethod> httpMethods = template.optionsForAllow(url());
		System.out.println(httpMethods.size());
		for(HttpMethod hm : httpMethods) {
			System.out.println(hm);
		}
	}
	
	//可以指定HttpMethod；能够设置头信息。比较灵活
	@Test
	public void exchange() {
		//重载6个方法
		
		HttpEntity<Object> httpEntity = null;
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Accpet", "application/json");
		httpEntity = new HttpEntity<Object>(headers);
		
		//不加HttpEntity等同于getForEntity
		ResponseEntity<Spittle> response = template.exchange(urlHasParam(), HttpMethod.GET, httpEntity, Spittle.class, 3L);
		System.out.println(response.getStatusCode());
		assertEquals(createSpittle(3), response.getBody());
	}
	
	@Test
	public void execute() {
		//RestTemplate的所有对外提供的操作方法，都是基于execute方法，其内部最终调用doExecute方法。
	}
	
	private Spittle createSpittle(long id) {
	    return new Spittle(id, "a new Spittle...", new Date(), -81.5811668, 28.4159649);
	}
	
	private Spittle updatedSpittle(long id) {
		return new Spittle(id, "a updated Spittle...", new Date(), -81.5811668, 28.4159649);
	}
	
	private String url() {
		return "http://localhost:8110/springiA4_16_restful_message_converters/spittles";
	}
	
	private String urlHasParam() {
		return url() + "/{id}";
	}
	
	/*
	 * RestTemplate定义了11个独立的操作，而每一个都有重载，这样一共是36个方法
	 * delete() 			在特定的URL上对资源执行HTTP DELETE操作
	 * exchange()			在URL上执行特定的HTTP方法，返回包含对象的ResponseEntity，
	 * 						这个对象是从响应体中映射得到的。（唯一一个重载6次）
	 * execute()			在URL上执行特定的HTTP方法，返回一个从响应体映射得到的对象
	 * getForEntity()		发送一个HTTP GET请求，返回的ResponseEntity包含了响应体所映射成的对象
	 * getForObject() 		发送一个HTTP GET请求，返回的请求体将映射为一个对象
	 * headForHeaders() 	发送HTTP HEAD请求，返回包含特定资源URL的HTTP头
	 * optionsForAllow() 	发送HTTP OPTIONS请求，返回对特定URL的Allow头信息
	 * postForEntity()		POST数据到一个URL，返回包含一个对象的ResponseEntity，这个对象
	 * 						是从响应体中映射得到的
	 * postForLocation() 	POST数据到一个URL，返回新创建资源的URL
	 * postForObject() 		POST数据到一个URL，返回根据响应体匹配形成的对象
	 * put() 				PUT资源到特定的URL
	 */
}
