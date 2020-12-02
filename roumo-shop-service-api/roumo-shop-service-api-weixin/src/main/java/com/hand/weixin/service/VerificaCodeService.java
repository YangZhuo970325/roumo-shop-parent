package com.hand.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "微信注册码验证接口")
public interface VerificaCodeService {
    
    /**
      * @desc 根据手机号验证token是否正确
      * @param phone 手机号
      * @param weixinCode 微信验证码
      * @date 2020/1/31 20:55
      * @return 
      **/
    @ApiOperation(value = "根据手机号验证token是否正确")
    @PostMapping("/verificaWeixinCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "phone", dataType = "String", required = true, value = "用户手机号码"),
            @ApiImplicitParam(paramType = "query", name = "weixinCode", dataType = "String", required = true, value = "微信注册码")
    })
    public BaseResponse<JSONObject> verificaWeixinCode(@RequestParam("phone") String phone, @RequestParam("weixinCode") String weixinCode);
}
