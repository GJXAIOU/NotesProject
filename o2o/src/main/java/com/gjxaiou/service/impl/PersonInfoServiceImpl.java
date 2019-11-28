package com.gjxaiou.service.impl;


import com.gjxaiou.dao.PersonInfoDao;
import com.gjxaiou.dto.PersonInfoExecution;
import com.gjxaiou.entity.PersonInfo;
import com.gjxaiou.enums.OperationStatusEnum;
import com.gjxaiou.enums.PersonInfoStateEnum;
import com.gjxaiou.exception.PersonInfoOperationException;
import com.gjxaiou.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 用户信息服务层接口实现类
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;


	@Override
	public PersonInfo queryInfoByUserId(long userId) {
		return personInfoDao.queryInfoByUserId(userId);
	}


	@Override
	public PersonInfoExecution insertPersonInfo(PersonInfo user) {
		// 空值判断
		if (user == null || user.getLocalAuthId() == null || user.getName() == null) {
			return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
		}
		// 设置默认信息
		user.setCreateTime(new Date());
		try {
			int effectedNum = personInfoDao.insertPersonInfo(user);
			if (effectedNum <= 0) {
				throw new PersonInfoOperationException("用户信息新增失败");
			} else {
				return new PersonInfoExecution(OperationStatusEnum.SUCCESS, user);
			}
		} catch (Exception e) {
			throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
		}
	}


	@Override
	public PersonInfoExecution updatePersonInfo(PersonInfo user) {
		// 空值判断
		if (user == null || user.getUserId() == null || user.getLocalAuthId() == null || user.getName() == null) {
			return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
		}
		// 设置默认信息
		user.setLastEditTime(new Date());
		try {
			int effectedNum = personInfoDao.updatePersonInfo(user);
			if (effectedNum <= 0) {
				throw new PersonInfoOperationException("用户信息修改失败");
			} else {
				return new PersonInfoExecution(OperationStatusEnum.SUCCESS, user);
			}
		} catch (Exception e) {
			throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
		}
	}

}
