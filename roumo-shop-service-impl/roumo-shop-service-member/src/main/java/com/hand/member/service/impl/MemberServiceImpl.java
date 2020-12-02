/**
 * 文件名：MemberServiceImpl.java
 * 描述：
 **/
package com.hand.member.service.impl;

import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.bean.utils.RoumoBeanUtils;
import com.hand.constants.Constants;
import com.hand.core.token.GenerateToken;
import com.hand.core.utils.MD5Util;
import com.hand.member.feign.WeiXinServiceFeign;
import com.hand.member.input.dto.UserLoginInpDTO;
import com.hand.member.mapper.UserMapper;
import com.hand.member.mapper.entity.UserDo;
import com.hand.member.output.dto.UserOutDTO;
import com.hand.member.service.MemberService;
import com.hand.type.TypeCastHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 16:03
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService{

    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1. 验证参数
        if(StringUtils.isEmpty(mobile)){
            return setResultError("手机号码不能为空");
        }
        // 2. 调用数据库根据手机号码查询用户信息
        UserDo userDo = userMapper.existMobile(mobile);
        if(userDo == null){
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户信息不存在");
        }
        // 3. 将DO转换成DTO
        UserOutDTO userOutDTO = RoumoBeanUtils.doToDto(userDo, UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> getUserInfo(String token) {
        // 1. 验证token参数
        if(StringUtils.isEmpty(token)){
            return setResultError("token不能为空");
        }
        // 2. 使用token查询redis中的userId
        String redisUserId = generateToken.getToken(token);
        if(StringUtils.isEmpty(redisUserId)){
            return setResultError("token已经失效或者token错误");
        }
        // 3. 使用userID查询数据库用户信息
        Long userId = TypeCastHelper.toLong(redisUserId);
        UserDo userDo = userMapper.findByUserId(userId);
        if(userDo == null){
            return setResultError("用户不存在");
        }
        // 4. DO转DTO
        UserOutDTO userOutDTO = RoumoBeanUtils.doToDto(userDo, UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1. 验证参数
        String mobile = userLoginInpDTO.getMobile();
        if(StringUtils.isEmpty(mobile)){
            return setResultError("手机号码不能为空！");
        }
        String password = userLoginInpDTO.getPassword();
        if(StringUtils.isEmpty(password)){
            return setResultError("密码不能为空！");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if(StringUtils.isEmpty(loginType)){
            return setResultError("登录类型不能为空！");
        }
        // 限制登录类型
        if(!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))){
            return setResultError("登录类型出现错误");
        }

        // 设备信息
        String deviceInfo = userLoginInpDTO.getDeviceInfo();
        if(StringUtils.isEmpty(deviceInfo)){
            return setResultError("设备信息为空");
        }

        // 2. 对登录密码进行加密
        String newPassword = MD5Util.MD5(password);

        // 3. 使用手机号+密码查询数据库，判断用户是否存在
        UserDo userDo = userMapper.login(mobile, newPassword);
        if(userDo == null){
            return setResultError("用户名或密码错误！");
        }
        return setResultSuccess(RoumoBeanUtils.doToDto(userDo, UserOutDTO.class));
    }
}
