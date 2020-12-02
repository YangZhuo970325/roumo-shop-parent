package com.hand.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseResponse;
import com.hand.pay.input.dto.PayCreatePayTokenDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

public interface PaymentTransacTokenService {
    
    /**
      * @desc 
      * @param payCreatePayTokenDto
      * @date 2020/3/6 14:33
      * @return 
      **/
    @GetMapping("/createPayToken")
    public BaseResponse<JSONObject> createPayToken(@Validated PayCreatePayTokenDto payCreatePayTokenDto);
}
