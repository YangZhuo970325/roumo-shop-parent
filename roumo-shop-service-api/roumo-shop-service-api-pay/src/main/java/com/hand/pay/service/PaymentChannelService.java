/**
 * 文件名：PaymentChannelService.java
 * 描述：
 **/
package com.hand.pay.service;

import com.hand.pay.output.dto.PaymentChannelDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 15:56
 */
public interface PaymentChannelService {

    @GetMapping("/selectAll")
    public List<PaymentChannelDTO> selectAll();
}
