/**
 * 文件名：MemberRegisterServiceFeign.java
 * 描述：
 **/
package com.hand.member.feign;

import com.hand.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/3
 * @date 2020/2/3 17:11
 */
@FeignClient("app-hand-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService{
}
