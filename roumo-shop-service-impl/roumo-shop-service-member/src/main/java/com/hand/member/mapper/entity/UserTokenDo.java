package com.hand.member.mapper.entity;


import com.hand.base.BaseDo;
import lombok.Data;

@Data
public class UserTokenDo extends BaseDo {
	/**
	 * 用户token
	 */
	private String token;
	/**
	 * 登陆类型
	 */
	private String loginType;

	/**
	 * 设备信息
	 */
	private String deviceInfo;
	/**
	 * 用户userId
	 */
	private Long userId;

}
