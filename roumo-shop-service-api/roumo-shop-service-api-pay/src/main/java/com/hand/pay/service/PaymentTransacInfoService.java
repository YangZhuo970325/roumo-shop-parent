/**
 * 文件名：PaymentTransacInfoService.java
 * 描述：
 **/
package com.hand.pay.service;

import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.pay.output.dto.PaymentTransacDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/6
 * @date 2020/3/6 16:02
 */
public interface PaymentTransacInfoService {
    @GetMapping("/getPaymentInfoByToken")
    public BaseResponse<PaymentTransacDTO> getPaymentInfoByToken(@RequestParam("token") String token);
}
