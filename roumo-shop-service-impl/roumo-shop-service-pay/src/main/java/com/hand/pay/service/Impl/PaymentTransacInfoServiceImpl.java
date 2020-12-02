/**
 * 文件名：PaymentTransacInfoServiceImpl.java
 * 描述：
 **/
package com.hand.pay.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.bean.utils.RoumoBeanUtils;
import com.hand.core.token.GenerateToken;
import com.hand.pay.mapper.PaymentTransactionMapper;
import com.hand.pay.mapper.entity.PaymentTransactionEntity;
import com.hand.pay.output.dto.PaymentTransacDTO;
import com.hand.pay.service.PaymentTransacInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc 通过token查询支付信息
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 16:05
 */
public class PaymentTransacInfoServiceImpl extends BaseApiService<PaymentTransacDTO> implements PaymentTransacInfoService{

    @Autowired
    private GenerateToken generateToken;
    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Override
    public BaseResponse<PaymentTransacDTO> getPaymentInfoByToken(String token) {

        //1.验证token是否为空
        if (StringUtils.isEmpty(token)) {
            return setResultError("token参数不能空!");
        }

        // 2.使用token查询redisPayMentTransacID
        String value = generateToken.getToken(token);
        if (StringUtils.isEmpty(value)) {
            return setResultError("该Token可能已经失效或者已经过期");
        }

        // 3.转换为整数类型
        Long transactionId = Long.parseLong(value);
        // 4.使用transactionId查询支付信息
        PaymentTransactionEntity paymentTransaction = paymentTransactionMapper.selectById(transactionId);
        if (paymentTransaction == null) {
            return setResultError("未查询到该支付信息");
        }

        return setResultSuccess(RoumoBeanUtils.doToDto(paymentTransaction, PaymentTransacDTO.class));
    }
}
