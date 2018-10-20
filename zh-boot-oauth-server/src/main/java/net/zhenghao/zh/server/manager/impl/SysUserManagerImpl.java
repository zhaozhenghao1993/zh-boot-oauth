package net.zhenghao.zh.server.manager.impl;

import net.zhenghao.zh.server.dao.SysUserMapper;
import net.zhenghao.zh.server.entity.SysUserEntity;
import net.zhenghao.zh.server.manager.SysUserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午1:50:35
 * SysUserManagerImpl.java
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {
	
	@Autowired
	private SysUserMapper sysUserMapper;


	@Override
	public List<SysUserEntity> listUser() {
		return sysUserMapper.listUser();
	}

	@Override
	public SysUserEntity findUserByUsername(String username) {
		return sysUserMapper.findUserByUsername(username);
	}
}
