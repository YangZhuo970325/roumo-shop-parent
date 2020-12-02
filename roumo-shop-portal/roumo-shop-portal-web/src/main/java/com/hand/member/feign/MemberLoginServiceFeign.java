package com.hand.member.feign;

import com.hand.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-hand-member")
public interface MemberLoginServiceFeign extends MemberLoginService{
}
