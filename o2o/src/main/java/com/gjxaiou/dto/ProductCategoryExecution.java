package com.gjxaiou.dto;


import com.gjxaiou.entity.ProductCategory;
import com.gjxaiou.enums.ProductCategoryStateEnum;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryExecution {

	/**
	 * 结果状态
	 */
	private int state;

	/**
	 * 状态标识
	 */
	private String stateInfo;

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}
	// 操作失败时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
}
