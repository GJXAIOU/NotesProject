package com.gjxaiou.service.impl;


import com.gjxaiou.dao.ShopDao;
import com.gjxaiou.dto.ShopExecution;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ShopStateEnum;
import com.gjxaiou.exception.ShopOperationException;
import com.gjxaiou.service.ShopService;
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;


	@Override
	@Transactional // 表示该方法需要事务处理
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws RuntimeException {
		// 如果店铺为空，抛出异常
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}

		// 1. 往数据库中增加这条店铺信息
			//  0 表示未上架，在审核中：Checked 状态
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());

			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				try {
					if (shopImgInputStream != null) {
			//2 这里需要 shop 对象中的 shopId 创建文件对象，因此需要传入 shop 对象，创建好之后将图片地址更新到 shop 实体类中
						addShopImg(shop, shopImgInputStream, fileName);
			//3， 更新店铺的图片地址
						effectedNum = shopDao.updateShop(shop);
						if (effectedNum <= 0) {
							throw new ShopOperationException("更新图片地址失败");
						}
					}
				} catch (Exception e) {
					throw new ShopOperationException("addShopImg error: "
							+ e.getMessage());
				}
				return new ShopExecution(ShopStateEnum.CHECK, shop);
			}
	}


	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 获取 shop 图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
		// shop 对象属性赋值
		shop.setShopImg(shopImgAddr);
	}
}
