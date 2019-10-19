package com.gjxaiou.service;

import com.gjxaiou.dto.ShopExecution;

import com.gjxaiou.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

	/**
	 * 创建商铺
	 * 
	 * @para shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException;

}
