/**
 * 文件名：MemberRegisterServiceImpl.java
 * 描述：
 **/
package com.hand.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.bean.utils.RoumoBeanUtils;
import com.hand.constants.Constants;
import com.hand.core.utils.MD5Util;
import com.hand.member.feign.VerificaCodeServiceFeign;
import com.hand.member.input.dto.UserInpDTO;
import com.hand.member.mapper.UserMapper;
import com.hand.member.mapper.entity.UserDo;
import com.hand.member.service.MemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/31
 * @date 2020/1/31 22:19
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;

    @Override
    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String registCode) {
        // 1. 参数验证
        /*String userName = userInpDTO.getUserName();
        if(StringUtils.isEmpty(userName)){
            return setResultError("用户名不能为空");
        }*/
        String mobile = userInpDTO.getMobile();
        if(StringUtils.isEmpty(mobile)){
            return setResultError("手机号码不能为空");
        }
        String password = userInpDTO.getPassword();
        if(StringUtils.isEmpty(password)){
            return setResultError("密码不能为空");
        }
        // 2. 验证注册码是否正确(会员调用微信接口实现注册码验证)
        BaseResponse<JSONObject> verificaCode = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);
        if(!verificaCode.getCode().equals(Constants.HTTP_RES_CODE_200)){
            return setResultError(verificaCode.getMsg());
        }

        // 3. 对用户密码进行加密
        String newPassword = MD5Util.MD5(password);
        userInpDTO.setPassword(newPassword);

        // 4. 将dto转化成do存入数据库中
        UserDo userDo = RoumoBeanUtils.dtoToDo(userInpDTO, UserDo.class);
        // 5. 调用数据库插入数据
        return userMapper.register(userDo)>0 ? setResultSuccess("注册成功") : setResultError("注册失败");
    }
}
