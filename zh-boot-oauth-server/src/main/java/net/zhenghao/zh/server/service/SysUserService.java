package net.zhenghao.zh.server.service;

import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.server.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月7日 上午9:07:06
 * SysUserService.java
 */
public interface SysUserService {
	
	List<SysUserEntity> listUser();

	SysUserEntity findUserByUsername(SysUserEntity user);

	SysUserEntity getUserByUsername(String username);
	
}
