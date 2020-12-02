package com.hand.base;

import lombok.Data;

/**
 * @desc: 微服务接口统一返回码
 * @author: yangzhuo-hd@139.com
 * @date: 2020-01-31 18:01:00
 */
@Data
public class BaseResponse<T> {

	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 返回
	 */
	private T data;
	// 分页

	public BaseResponse() {

	}

	public BaseResponse(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}
