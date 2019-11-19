package com.gjxaiou.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjxaiou.cache.JedisUtil;
import com.gjxaiou.dao.HeadLineDao;
import com.gjxaiou.dto.HeadLineExecution;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.entity.HeadLine;
import com.gjxaiou.enums.HeadLineStateEnum;
import com.gjxaiou.enums.OperationStatusEnum;
import com.gjxaiou.exception.HeadLineOperationException;
import com.gjxaiou.service.CacheService;
import com.gjxaiou.service.HeadLineService;
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PathUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.omg.CORBA.IMP_LIMIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private CacheService cacheService;

	private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);


	@Override
	@Transactional
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		List<HeadLine> headLineList = null;
		ObjectMapper mapper = new ObjectMapper();
		String key = HL_LIST_KEY;
		if (headLineCondition.getEnableStatus() != null) {
			key = key + "_" + headLineCondition.getEnableStatus();
		}
		// redis中不存在key，则设值
		if (!jedisKeys.exists(key)) {
			headLineList = headLineDao.queryHeadLine(headLineCondition);
			// 将列表转为jsonString
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(headLineList);
				jedisStrings.set(key, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			}
		} else {
			String jsonString = jedisStrings.get(key);
			// 将jsonString转为list
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
			try {
				headLineList = mapper.readValue(jsonString, javaType);
			} catch (IOException e) {
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			}
		}
		return headLineList;
	}

	@Override
	@Transactional
	public HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder headLineImg) {
		if (headLine != null) {
			headLine.setCreateTime(new Date());
			headLine.setLastEditTime(new Date());
			// 添加图片
			if (headLineImg != null) {
				addThumbnail(headLine, headLineImg);
			}
			try {
				// 插入图片
				int effectedNum = headLineDao.insertHeadLine(headLine);
				if (effectedNum > 0) {
					String prefix = HL_LIST_KEY;
					Set<String> keySet = jedisKeys.keys(prefix + "*");
					for (String key : keySet) {
						jedisKeys.del(key);
					}
					return new HeadLineExecution(OperationStatusEnum.SUCCESS, headLine);
				} else {
					return new HeadLineExecution(HeadLineStateEnum.EDIT_ERROR);
				}
			} catch (Exception e) {
				throw new HeadLineOperationException(HeadLineStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
			}
		} else {
			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder headLineImg) {
		if (headLine.getLineId() != null && headLine.getLineId() > 0) {
			headLine.setLastEditTime(new Date());
			// 图片存在
			if (headLineImg != null) {
				HeadLine tempHeadLine = headLineDao.selectHeadLineById(headLine.getLineId());
				if (tempHeadLine.getLineImg() != null) {
					ImageUtil.deleteFileOrDirectory(tempHeadLine.getLineImg());
				}
				addThumbnail(headLine, headLineImg);
			}
			try {
				int effectedNum = headLineDao.updateHeadLine(headLine);
				if (effectedNum > 0) {
					// 删除缓存信息
					cacheService.removeFromCache(HL_LIST_KEY);
					return new HeadLineExecution(OperationStatusEnum.SUCCESS, headLine);
				} else {
					return new HeadLineExecution(HeadLineStateEnum.EMPTY);
				}
			} catch (Exception e) {
				throw new RuntimeException(HeadLineStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
			}
		} else {
			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HeadLineExecution removeHeadLine(long headLineId) {
		if (headLineId > 0) {
			try {
				HeadLine tempHeadLine = headLineDao.selectHeadLineById(headLineId);
				// 删除图片信息
				if (tempHeadLine.getLineImg() != null) {
					ImageUtil.deleteFileOrDirectory(tempHeadLine.getLineImg());
				}
				int effectedNum = headLineDao.deleteHeadLine(headLineId);
				if (effectedNum > 0) {
					// 删除缓存信息
					cacheService.removeFromCache(HL_LIST_KEY);
					return new HeadLineExecution(OperationStatusEnum.SUCCESS);
				} else {
					return new HeadLineExecution(HeadLineStateEnum.EMPTY);
				}
			} catch (Exception e) {
				throw new RuntimeException(HeadLineStateEnum.DELETE_ERROR.getStateInfo() + e.getMessage());
			}
		} else {
			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
		if (headLineIdList != null && headLineIdList.size() > 0) {
			try {
				List<HeadLine> headLineList = headLineDao.selectHeadLineByIds(headLineIdList);
				for (HeadLine headLine : headLineList) {
					if (headLine.getLineImg() != null) {
						ImageUtil.deleteFileOrDirectory(headLine.getLineImg());
					}
				}
				// 批量删除
				int effectedNum = headLineDao.batchDeleteHeadLine(headLineIdList);
				if (effectedNum > 0) {
					// 删除缓存信息
					cacheService.removeFromCache(HL_LIST_KEY);
					return new HeadLineExecution(OperationStatusEnum.SUCCESS);
				} else {
					return new HeadLineExecution(HeadLineStateEnum.EMPTY);
				}
			} catch (Exception e) {
				throw new RuntimeException(HeadLineStateEnum.DELETE_ERROR.getStateInfo() + e.getMessage());
			}
		} else {
			return new HeadLineExecution(HeadLineStateEnum.EMPTY);
		}
	}

	/**
	 * 添加图片信息
	 *
	 * @param headLine
	 * @param headLineImg
	 */
	private void addThumbnail(HeadLine headLine, ImageHolder headLineImg) {
		String dest = PathUtil.getHeadLineImagePath();
		String thumbnailAddr = ImageUtil.generateThumbnail(headLineImg, dest);
		headLine.setLineImg(thumbnailAddr);
	}

}
