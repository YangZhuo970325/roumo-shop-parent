/**
 * 文件名：IndexController.java
 * 描述：
 **/
package com.hand.portal.controller;

import com.hand.base.BaseResponse;
import com.hand.member.feign.MemberServiceFeign;
import com.hand.member.output.dto.UserOutDTO;
import com.hand.web.base.BaseWebController;
import com.hand.web.constants.Constants;
import com.hand.web.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/3
 * @date 2020/2/3 13:11
 */
@Controller
public class IndexController extends BaseWebController{

    /**
      * @desc 跳转到index页面
      * @return
      **/
    private static final String INDEX_FTL = "index";

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        // 1. 从cookie中拿到token
        String token = CookieUtils.getCookieValue(request, Constants.LOGIN_TOKEN_COOKIENAME, true);
        if(!StringUtils.isEmpty(token)){
            // 2. 调用会员服务接口，查询会员信息
            BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getUserInfo(token);
            if(isSuccess(userInfo)){
                UserOutDTO data = userInfo.getData();
                if(data != null){
                    String mobile = data.getMobile();
                    //对手机号码脱敏
                    String desensMobile = mobile.replace("(\\d{3})\\d{4}(\\d{4})", "$s1****$s2");
                    model.addAttribute("desensMobile", desensMobile);
                }
            }
        }

        return INDEX_FTL;
    }
}
