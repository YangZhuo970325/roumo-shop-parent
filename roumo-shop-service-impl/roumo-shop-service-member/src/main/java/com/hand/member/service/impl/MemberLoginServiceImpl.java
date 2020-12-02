/**
 * 文件名：MemberLoginServiceImpl.java
 * 描述：
 **/
package com.hand.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.constants.Constants;
import com.hand.core.token.GenerateToken;
import com.hand.core.transaction.RedisDataSoureceTransaction;
import com.hand.core.utils.MD5Util;
import com.hand.member.input.dto.UserLoginInpDTO;
import com.hand.member.mapper.UserMapper;
import com.hand.member.mapper.UserTokenMapper;
import com.hand.member.mapper.entity.UserDo;
import com.hand.member.mapper.entity.UserTokenDo;
import com.hand.member.service.MemberLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc 用户登录接口实现类
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/2
 * @date 2020/2/2 14:15
 */
@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 手动事务工具类
     */
    @Autowired
    private RedisDataSoureceTransaction manualTransaction;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
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

        TransactionStatus transactionStatus = null;
        try {
            // 用户登录Token Session的区别
            // 用户每一个端登录成功后,会对应生成一个token（临时且唯一）存放在redis中作为key,userId作为value
            // 4. 获取userid
            Long userId = userDo.getUserId();
            // 5. 根据userId+loginType 查询当前登录类型账号之前是否由登录过，如果登录过，清除之前redis的token
            UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
            // 开启手动事务
            transactionStatus = manualTransaction.begin();
            if(userTokenDo != null){
                String token = userTokenDo.getToken();
                //如果开启事务，删除的时候会返回false
                String kk =  generateToken.getToken(token);
                Boolean isremoveToken = generateToken.removeToken(token);
                int updateTokenAvailability = userTokenMapper.updateTokenAvailability(token);
                if(!toDaoResult(updateTokenAvailability)){
                    manualTransaction.rollback(transactionStatus);
                    return setResultError("系统错误!");
                }
            }

            // 6. 生成对应用户令牌存放在redis中
            String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
            String newToken = generateToken.createToken(keyPrefix, userId + "");

            // 7. 插入新的token
            UserTokenDo userToken = new UserTokenDo();
            userToken.setUserId(userId);
            userToken.setLoginType(userLoginInpDTO.getLoginType());
            userToken.setToken(newToken);
            userToken.setDeviceInfo(deviceInfo);
            int insertUserToken = userTokenMapper.insertUserToken(userToken);
            if (!toDaoResult(insertUserToken)) {
                manualTransaction.rollback(transactionStatus);
                return setResultError("系统错误!");
            }

            JSONObject data = new JSONObject();
            data.put("token", newToken);
            // 手动提交事务
            manualTransaction.commit(transactionStatus);
            return setResultSuccess(data);
        }catch (Exception e){
            try{
                // 回滚事务
                manualTransaction.rollback(transactionStatus);
            } catch (Exception e1){

            }
            return setResultError("系统错误");
        }

    }
}
