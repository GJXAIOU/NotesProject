package com.gjxaiou.dao;


import com.gjxaiou.BaseTest;
import com.gjxaiou.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// 上面是设置调试执行顺序，该参数表示按照名字排序进行进行 ，a,b,c,....
public class ProductCategoryDaoTest extends BaseTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testAQueryByShopId() {
		long shopId = 1;
		List<ProductCategory> result = productCategoryDao.queryProductCategoryList(shopId);

		assertTrue(result.size() > 0);
	}

	/**
	 * 测试批量插入
	 */
	@Test
	public void testBBatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(1l);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(1l);

		List<ProductCategory> productCategoryList = new ArrayList<>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);

		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}

	@Test
	public void testCDeleteProductCategory() {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory pc : productCategoryList) {
			if ("商品类别1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1, effectedNum);
			}
		}
	}

}
