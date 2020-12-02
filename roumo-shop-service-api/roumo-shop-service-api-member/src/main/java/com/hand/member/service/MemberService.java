/**
 * 文件名：MemberService.java
 * 描述：
 **/
package com.hand.member.service;

import com.hand.base.BaseResponse;
import com.hand.member.input.dto.UserLoginInpDTO;
import com.hand.member.output.dto.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @desc 会员服务接口
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 15:57
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    /**
      * @desc 根据手机号查询是否已经存在，如果存在返回当前用户信息
      * @param mobile
      * @date 2020/2/2 16:04
      * @return com.hand.base.BaseResponse<com.hand.member.output.dto.UserOutDTO>
      **/
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码")})
    @PostMapping("/existMobile")
    BaseResponse<UserOutDTO> existMobile(@RequestParam("mobile") String mobile);

    /**
      * @desc 根据token查询用户信息
      * @param token
      * @date 2020/2/2 16:06
      * @return com.hand.base.BaseResponse<com.hand.member.output.dto.UserOutDTO>
      **/
    @ApiOperation(value = "根据token获取用户信息")
    @PostMapping("/getUserInfo")
    BaseResponse<UserOutDTO> getUserInfo(@RequestParam("token") String token);

    /**
      * @desc SSO认证系统登录接口
      * @param userLoginInpDTO
      * @date 2020/2/20 18:25
      * @return 
      **/
    @PostMapping("/ssoLogin")
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO);
    
}
