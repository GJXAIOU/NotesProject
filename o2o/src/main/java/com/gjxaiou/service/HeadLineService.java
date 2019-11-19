package com.gjxaiou.service;


import com.gjxaiou.dto.HeadLineExecution;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.entity.HeadLine;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {
	public final static String HL_LIST_KEY = "headLineList";
	/**
	 * 根据传入的条件返回指定的头条列表
	 * 
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;

	/**
	 * 添加头条记录
	 *
	 * @param headLine
	 * @param headLineImg
	 * @return
	 */
	HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder headLineImg);

	/**
	 * 修改头条记录
	 *
	 * @param headLine
	 * @param headLineImg
	 * @return
	 */
	HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder headLineImg);

	/**
	 * 删除头条
	 *
	 * @param headLineId
	 * @return
	 */
	HeadLineExecution removeHeadLine(long headLineId);

	/**
	 * 删除头条列表
	 *
	 * @param headLineIdList
	 * @return
	 */
	HeadLineExecution removeHeadLineList(List<Long> headLineIdList);
}
