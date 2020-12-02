/**
 * 文件名：LoginController.java
 * 描述：
 **/
package com.hand.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseResponse;
import com.hand.member.controller.req.vo.LoginVo;
import com.hand.member.controller.req.vo.RegisterVo;
import com.hand.member.feign.MemberLoginServiceFeign;
import com.hand.member.input.dto.UserLoginInpDTO;
import com.hand.web.base.BaseWebController;
import com.hand.web.bean.RoumoBeanUtils;
import com.hand.web.constants.Constants;
import com.hand.web.utils.CookieUtils;
import com.hand.web.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/3
 * @date 2020/2/3 13:14
 */
@Controller
public class LoginController extends BaseWebController{

    /**
      * @desc 跳转到登录页面
      **/
    private static final String MB_LOGIN_FTL = "member/login";

    /**
      * @desc 重定向到首页
      **/
    private static final String RIDIRECT_INDEX = "redirect:/";



    @Autowired
    private MemberLoginServiceFeign memberLoginServiceFeign;

    /**
      * @desc 跳转页面
      **/
    @GetMapping("/login")
    public String getLogin(){
        return MB_LOGIN_FTL;
    }

    /**
     * @desc 接收请求参数
     **/
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("registerVo") @Validated LoginVo loginVo, BindingResult bindingResult,
                            Model model, HttpServletRequest request, HttpServletResponse response,
                            HttpSession httpSession){
        // 1. 图形验证码判断
        String graphicCode = loginVo.getGraphicCode();
        if(!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)){
            setErrorMsg(model, "图形验证码不正确");
            return MB_LOGIN_FTL;
        }

        // 2. 将vo转换为dto调用会员登录接口
        UserLoginInpDTO userLoginInpDTO = RoumoBeanUtils.voToDo(loginVo, UserLoginInpDTO.class);
        userLoginInpDTO.setLoginType(com.hand.constants.Constants.MEMBER_LOGIN_TYPE_PC);
        String info = webBrowserInfo(request);
        userLoginInpDTO.setDeviceInfo(info);
        BaseResponse<JSONObject> login = memberLoginServiceFeign.login(userLoginInpDTO);

        if(!isSuccess(login)){
            setErrorMsg(model, login.getMsg());
            return MB_LOGIN_FTL;
        }

        // 3. 登录成功 保持会话信息，将token存放到cookie里面
        // 首页读取cookie里面的token，查询用户信息到首页展示
        JSONObject data = login.getData();
        String token = data.getString("token");
        CookieUtils.setCookie(request, response, Constants.LOGIN_TOKEN_COOKIENAME, token);

        return RIDIRECT_INDEX;
    }

}
