/**
 * 文件名：PaymentTransacDTO.java
 * 描述：
 **/
package com.hand.pay.output.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 14:48
 */
@Data
public class PaymentTransacDTO {
    /** 主键ID */
    private Integer id;
    /** 支付金额 */
    private Long payAmount;
    /** 支付状态;0待支付1支付完成 2支付超时3支付失败 */
    private Integer paymentStatus;
    /** 用户ID */
    private Integer userId;
    /** 订单号码 */
    private String orderId;

    /** 创建时间 */
    private Date createdTime;

    /**
     * 第三方支付id 支付宝、银联等
     */
    private String partyPayId;

    /**
     * 使用雪花算法生产 支付系统 支付id
     */
    private String paymentId;
}
