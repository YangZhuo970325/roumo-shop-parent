/**
 * 文件名：GlobalExceptionHandler.java
 * 描述：
 **/
package com.hand.core.error;

import com.alibaba.fastjson.JSONObject;
import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/1
 * @date 2020/2/1 21:47
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseApiService<JSONObject>{
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse<JSONObject> exceptionHandler(Exception e){
        log.info("###全局捕获异常###,error:{}", e);
        return setResultError("系统错误!");
    }
}
