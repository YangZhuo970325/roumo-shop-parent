/**
 * 文件名：WeiXinServiceFeign.java
 * 描述：
 **/
package com.hand.member.feign;

import com.hand.weixin.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 16:10
 */
@FeignClient("app-hand-weixin")
public interface WeiXinServiceFeign extends WeiXinService{
}
