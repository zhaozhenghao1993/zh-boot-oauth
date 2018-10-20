package net.zhenghao.zh.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午3:16:19
 * SysUserEntity.java
 */
public class SysUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;


	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;

	/**
	 * 修改时间
	 */
	private Timestamp gmtModified;

	
	public SysUserEntity(){
		super();
	}

	public SysUserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}
}
