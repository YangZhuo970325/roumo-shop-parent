/**
 * 文件名：VerifyController.java
 * 描述：
 **/
package com.hand.code;

import com.hand.web.utils.RandomValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/3
 * @date 2020/2/3 16:25
 */
@Controller
public class VerifyController {
    /**
      * @desc 生成验证码
      **/
    @RequestMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("image/jpeg");
            response.setHeader("Param", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCodeUtil = new RandomValidateCodeUtil();
            randomValidateCodeUtil.getRandcode(request, response);
        } catch (Exception e){
            
        }
    }
}
