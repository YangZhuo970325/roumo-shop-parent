package com.hand.member.feign;

import com.hand.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-hand-member")
public interface MemberServiceFeign extends MemberService {
}
