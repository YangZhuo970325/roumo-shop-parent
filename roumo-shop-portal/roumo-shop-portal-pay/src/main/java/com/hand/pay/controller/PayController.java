/**
 * 文件名：PayController.java
 * 描述：
 **/
package com.hand.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/22
 * @date 2020/2/22 10:38
 */
@Controller
public class PayController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
