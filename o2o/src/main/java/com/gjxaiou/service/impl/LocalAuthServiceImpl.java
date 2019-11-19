package com.gjxaiou.service.impl;


import com.gjxaiou.dao.LocalAuthDao;
import com.gjxaiou.dto.LocalAuthExecution;
import com.gjxaiou.entity.LocalAuth;
import com.gjxaiou.enums.LocalAuthStateEnum;
import com.gjxaiou.enums.OperationStatusEnum;
import com.gjxaiou.exception.LocalAuthOperationException;
import com.gjxaiou.service.LocalAuthService;
import com.gjxaiou.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: 本地用户信息服务层接口实现
 *
 * @author tyronchen
 * @date 2019年5月20日
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;


	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUsernameAndPwd(username, MD5.getMd5(password));
	}


	@Override
	public LocalAuth getLocalAuthByUsername(String username) {
		return localAuthDao.queryLocalByUsername(username);
	}


	@Override
	public LocalAuth queryLocalByLocalAuthId(long localAuthId) {
		return localAuthDao.queryLocalByLocalAuthId(localAuthId);
	}


	@Override
	@Transactional
	public LocalAuthExecution saveLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// 空值判断
		if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		// 保存数据
		localAuth.setCreateTime(new Date());
		localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
		try {
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new LocalAuthOperationException("用户账号新增失败");
			} else {
				return new LocalAuthExecution(OperationStatusEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// 空值判断, 传入的 localAuth 账号密码，用户信息特别是 userId 不能为空，否则直接返回错误；
		if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null || localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}

		// 查询该账号是否已经绑定过平台账号
		LocalAuth tempAuth =
				localAuthDao.queryLocalByLocalAuthId(localAuth.getPersonInfo().getUserId());
		// 如绑定过则直接推迟，保证账号的唯一性
		if (tempAuth !=  null){
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try{
			// 如果没有绑定过则创建一个平台账号与该用户绑定
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			// 对密码进行 MD5 加密之后存储
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			// 判断时候创建成功
			if (effectedNum <= 0){
				throw new LocalAuthOperationException("账号绑定失败");
			}else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			}
		}catch (Exception e){
			throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
		}
	}


	@Override
	public LocalAuthExecution modifyLocalAuth(String username, String password, String newPassword)
			throws LocalAuthOperationException {
		// 非空判断
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(newPassword)) {
			try {
				int effectedNum = localAuthDao.updateLocalAuth(username, MD5.getMd5(password), MD5.getMd5(newPassword),
						new Date());
				if (effectedNum <= 0) {
					return new LocalAuthExecution(LocalAuthStateEnum.ERROR_UPDATE);
				} else {
					return new LocalAuthExecution(OperationStatusEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new LocalAuthOperationException("updateLocalAuth error:" + e.getMessage());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
