package com.hand.member.feign;

import com.hand.weixin.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-hand-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService{
}
