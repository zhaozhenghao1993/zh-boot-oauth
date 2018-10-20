package net.zhenghao.zh.server.service.impl;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.server.entity.SysUserEntity;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.common.utils.MD5Utils;
import net.zhenghao.zh.server.manager.SysUserManager;
import net.zhenghao.zh.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午9:10:21
 * SysUserServiceImpl.java
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserManager sysUserManager;


	@Override
	public List<SysUserEntity> listUser() {
		return sysUserManager.listUser();
	}

	@Override
	public SysUserEntity findUserByUsername(SysUserEntity user) {
		SysUserEntity userEntity = sysUserManager.findUserByUsername(user.getUsername());
		if (userEntity != null && userEntity.getPassword().equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public SysUserEntity getUserByUsername(String username) {
		return sysUserManager.findUserByUsername(username);
	}
}
