/**
 * 文件名：PayCreatePayTokenDto.java
 * 描述：
 **/
package com.hand.pay.input.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 14:39
 */
@Data
public class PayCreatePayTokenDto {

    /**
     * 支付金额
     */
    @NotNull(message = "支付金额不能为空")
    private Long payAmount;
    /**
     * 订单号码
     */
    @NotNull(message = "订单号码不能为空")
    private String orderId;

    /**
     * userId
     */
    @NotNull(message = "userId不能空")
    private Long userId;
}
