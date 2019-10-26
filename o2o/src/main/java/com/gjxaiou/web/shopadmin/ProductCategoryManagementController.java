package com.gjxaiou.web.shopadmin;


import com.gjxaiou.dto.ProductCategoryExecution;
import com.gjxaiou.dto.Result;
import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.entity.Shop;
import com.gjxaiou.enums.ProductCategoryStateEnum;
import com.gjxaiou.exception.ProductCategoryOperationException;
import com.gjxaiou.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopAdmin")
public class ProductCategoryManagementController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getProductCategoryList", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		} else {
			ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false, ps.getState(), ps.getStateInfo());
		}
	}

	// 批量标签
	@RequestMapping(value = "/addProductCategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

		for (ProductCategory productCategory : productCategoryList) {
			productCategory.setShopId(currentShop.getShopId());
		}

		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution execution = productCategoryService
						.batchAddProductCategory(productCategoryList);
				if (execution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errMsg", execution.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				result.put("success", false);
				result.put("errMsg", e.toString());
			}
		} else {
			result.put("success", false);
			result.put("errMsg", "请至少输入一个商品类别");
		}

		return result;
	}

	@RequestMapping(value = "/removeProductCategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,
						currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e) {
				result.put("success", false);
				result.put("errMsg", e.toString());
			}
		} else {
			result.put("success", false);
			result.put("errMsg", "请至少选择一个商品类别");
		}
		return result;
	}

}
