package com.hand.web.bean;

import org.springframework.beans.BeanUtils;

/**
 * @desc 实体类转换工具
 * 
 *
 */

public class RoumoBeanUtils<Vo, Do> {

	/**
	 * Vo 转换为Do 工具类
	 * 
	 * @param voEntity
	 * @param doClass
	 * @return
	 */
		public static <Do> Do voToDo(Object voEntity, Class<Do> doClass) {
		// 判断vo是否为空!
		if (voEntity == null) {
			return null;
		}
		// 判断DoClass 是否为空
		if (doClass == null) {
			return null;
		}
		try {
			Do newInstance = doClass.newInstance();
			BeanUtils.copyProperties(voEntity, newInstance);
			// Dto转换Do
			return newInstance;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * do 转换为Dto 工具类
	 * 
	 * @param dtoEntity
	 * @param doEntity
	 * @return
	 */
	public static <Vo> Vo doToVo(Object doEntity, Class<Vo> voClass) {
		// 判断do是否为空!
		if (doEntity == null) {
			return null;
		}
		// 判断voClass 是否为空
		if (voClass == null) {
			return null;
		}
		try {
			Vo newInstance = voClass.newInstance();
			BeanUtils.copyProperties(doEntity, newInstance);
			// Do转换Vo
			return newInstance;
		} catch (Exception e) {
			return null;
		}
	}
	// 后面集合类型带封装
}
