package net.zhenghao.zh.server.manager;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.server.entity.SysUserEntity;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午1:42:18
 * SysUserManager.java
 */
public interface SysUserManager {

	List<SysUserEntity> listUser();

	SysUserEntity findUserByUsername(String username);

}
