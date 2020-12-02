package com.hand.member.service;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseResponse;
import com.hand.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
  * @desc 用户登录接口服务
  * @date 2020/2/2 14:13
  * @author yangzhuo-hd@139.com
  **/
@Api(tags = "用户登录服务接口")
public interface MemberLoginService {
    
    /**
      * @desc 用户登录接口
      * @param userLoginInpDTO
      * @date 2020/2/2 14:10
      * @return 
      **/
    @PostMapping("/login")
    @ApiOperation(value = "会员用户登录信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
