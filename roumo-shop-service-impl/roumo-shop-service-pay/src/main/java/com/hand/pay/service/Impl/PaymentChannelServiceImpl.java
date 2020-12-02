/**
 * 文件名：PaymentChannelServiceImpl.java
 * 描述：
 **/
package com.hand.pay.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.pay.mapper.PaymentChannelMapper;
import com.hand.pay.mapper.entity.PaymentChannelEntity;
import com.hand.pay.output.dto.PaymentChannelDTO;
import com.hand.pay.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 15:57
 */
public class PaymentChannelServiceImpl extends BaseApiService<JSONObject> implements PaymentChannelService{

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Override
    public List<PaymentChannelDTO> selectAll() {
        List<PaymentChannelEntity> paymentChanneList = paymentChannelMapper.selectAll();
        return MapperUtils.mapAsList(paymentChanneList, PaymentChannelDTO.class);
    }
}
