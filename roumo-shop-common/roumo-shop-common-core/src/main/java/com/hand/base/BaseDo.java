package com.hand.base;

import lombok.Data;

import java.util.Date;

/**
 * @description:BaseDo
 * @author: yangzhuo-hd@139.com
 * @date: 2020-02-02 17:03:17
 */
@Data
public class BaseDo {
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
	 * id
	 */
	private Long id;

	/**
	 * 是否可用 0可用 1不可用
	 */
	private char isAvailability;
}
