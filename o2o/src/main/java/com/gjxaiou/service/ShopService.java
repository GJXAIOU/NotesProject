package com.gjxaiou.service;

import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ShopExecution;

import com.gjxaiou.entity.Shop;
import com.gjxaiou.exception.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.InputStream;

public interface ShopService {

	/**
	 * 注册店铺信息，包括对图片的处理
	 * @para shop
	 * @return ShopExecution 里面包括店铺实体类和处理的状态值
	 * @throws Exception
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws RuntimeException;

	/**
	 * 通过店铺 Id 获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);

	/**
	 * 更新店铺信息，包括对图片的处理
	 * @param shop 店铺实体类
	 * @param shopImgInputStream 图片流
	 * @param fileName 文件名
	 * @return shopExecution 封装了店铺信息和状态信息
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;


	/**
	 * 根据shopCondition筛选条件分页查询店铺
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

}
