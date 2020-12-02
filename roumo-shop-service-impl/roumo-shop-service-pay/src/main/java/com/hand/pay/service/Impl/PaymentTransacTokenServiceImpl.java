/**
 * 文件名：PaymentTransacTokenServiceImpl.java
 * 描述：
 **/
package com.hand.pay.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.core.token.GenerateToken;
import com.hand.pay.input.dto.PayCreatePayTokenDto;
import com.hand.pay.mapper.PaymentTransactionMapper;
import com.hand.pay.mapper.entity.PaymentTransactionEntity;
import com.hand.pay.service.PaymentTransacTokenService;
import com.hand.twitter.SnowflakeIdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 15:05
 */
@RestController
public class PaymentTransacTokenServiceImpl extends BaseApiService<JSONObject> implements PaymentTransacTokenService {

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<JSONObject> createPayToken(PayCreatePayTokenDto payCreatePayTokenDto) {

        String orderId = payCreatePayTokenDto.getOrderId();
        if (StringUtils.isEmpty(orderId)) {
            return setResultError("订单号码不能为空!");
        }
        Long payAmount = payCreatePayTokenDto.getPayAmount();
        if (payAmount == null) {
            return setResultError("金额不能为空!");
        }
        Long userId = payCreatePayTokenDto.getUserId();
        if (userId == null) {
            return setResultError("userId不能为空!");
        }

        // 2.将输入插入数据库中 待支付记录
        PaymentTransactionEntity paymentTransactionEntity = new PaymentTransactionEntity();
        paymentTransactionEntity.setOrderId(orderId);
        paymentTransactionEntity.setPayAmount(payAmount);
        paymentTransactionEntity.setUserId(userId);

        // 使用雪花算法 生成全局id
        paymentTransactionEntity.setPaymentId(SnowflakeIdUtils.nextId());
        int result = paymentTransactionMapper.insertPaymentTransaction(paymentTransactionEntity);
        if (!toDaoResult(result)) {
            return setResultError("系统错误!");
        }

        // 获取主键id
        Long payId = paymentTransactionEntity.getId();
        if (payId == null) {
            return setResultError("系统错误!");
        }

        // 3.生成对应支付令牌
        String keyPrefix = "pay_";
        String token = generateToken.createToken(keyPrefix, payId + "");
        JSONObject dataResult = new JSONObject();
        dataResult.put("token", token);

        return setResultSuccess(dataResult);
    }
}
