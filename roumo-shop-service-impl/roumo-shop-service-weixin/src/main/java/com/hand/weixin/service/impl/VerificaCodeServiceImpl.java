/**
 * 文件名：VerificaCodeServiceImpl.java
 * 描述：
 **/
package com.hand.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.constants.Constants;
import com.hand.core.utils.RedisUtil;
import com.hand.weixin.service.VerificaCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/31
 * @date 2020/1/31 21:03
 */
@RestController
public class VerificaCodeServiceImpl extends BaseApiService<JSONObject> implements VerificaCodeService{

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {

        //1. 验证参数是否为空
        if(StringUtils.isEmpty(phone)){
            return setResultError("手机号码不能为空");
        }
        if(StringUtils.isEmpty(weixinCode)){
            return setResultError("注册码不能为空");
        }

        //2. 根据手机号码查询redis返回对应的注册码
        String weixinCodeKey = Constants.WEIXINCODE_KEY + phone;
        String redisCode = redisUtil.getString(weixinCodeKey);
        if(StringUtils.isEmpty(redisCode)){
            return setResultError("注册码可能已经过期");
        }
        //3. redis中的注册码与传递参数的weixinCode进行对比
        if(!redisCode.equals(weixinCode)){
            return setResultError("注册码不正确");
        }
        //4. 比对成功，移除value
        redisUtil.delKey(weixinCodeKey);
        return setResultSuccess("验证码比对正确");
    }

}
