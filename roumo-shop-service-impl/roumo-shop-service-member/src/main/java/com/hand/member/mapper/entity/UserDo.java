package com.hand.member.mapper.entity;

import java.sql.Date;

import lombok.Data;

/**
 * @description: 用户实体类DO
 * @author: yangzhuo-hd@139.com
 * @date: 2020年2月1日 16:53:17
 */
@Data
public class UserDo {

	/**
	 * userid
	 */

	private Long userId;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 性别 0 男 1女
	 */
	private char sex;
	/**
	 * 年龄
	 */
	private Long age;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 *
	 */
	private Date updateTime;
	/**
	 * 账号是否可以用 1 正常 0冻结
	 */
	private char isAvalible;
	/**
	 * 用户头像
	 */
	private String picImg;
	/**
	 * 用户关联 QQ 开放ID
	 */
	private String qqOpenid;
	/**
	 * 用户关联 微信 开放ID
	 */
	private String wxOpenid;
}
