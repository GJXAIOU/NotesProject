package com.gjxaiou.dto;


import com.gjxaiou.entity.HeadLine;
import com.gjxaiou.enums.HeadLineStateEnum;
import com.gjxaiou.enums.OperationStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: 封装操作HeadLine的返回结果，包括操作状态和HeadLine信息
 *
 * @author tyronchen
 * @date 2019年1月23日
 */
@Getter
@Setter
public class HeadLineExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	// 操作的headLine
	private HeadLine headLine;

	// 获取的列表
	private List<HeadLine> headLineList;

	public HeadLineExecution() {
	}

	// 失败的构造器
	public HeadLineExecution(HeadLineStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 失败的构造器
	public HeadLineExecution(OperationStatusEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public HeadLineExecution(OperationStatusEnum success, HeadLine headLine) {
		this.state = success.getState();
		this.stateInfo = success.getStateInfo();
		this.headLine = headLine;
	}

	// 成功的构造器
	public HeadLineExecution(OperationStatusEnum stateEnum, List<HeadLine> headLineList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.headLineList = headLineList;
	}



}
