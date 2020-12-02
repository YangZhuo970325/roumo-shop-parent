/**
 * 文件名：MemberServiceFeign.java
 * 描述：
 **/
package com.xxl.sso.server.feign;

import com.hand.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/21
 * @date 2020/2/21 11:11
 */
@FeignClient("app-hand-member")
public interface MemberServiceFeign extends MemberService{
}
