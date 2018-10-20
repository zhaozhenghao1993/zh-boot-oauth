package net.zhenghao.zh.server.dao;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.server.entity.SysUserEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 系统用户dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 上午10:42:58
 * SysUserMapper.java
 */
@MapperScan
public interface SysUserMapper{

	List<SysUserEntity> listUser();

	SysUserEntity findUserByUsername(String username);
}
