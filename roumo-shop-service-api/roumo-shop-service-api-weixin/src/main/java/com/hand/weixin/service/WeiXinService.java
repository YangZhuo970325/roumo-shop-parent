/**
 * 文件名：WeiXinService.java
 * 描述：
 **/
package com.hand.weixin.service;

import com.hand.base.BaseResponse;
import com.hand.weixin.input.dto.AppDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @desc 微信服务接口
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 15:15
 */
@Api(tags = "微信服务接口")
public interface WeiXinService {

    /**
      * @desc 获取应用接口
      * @param
      * @date 2020/1/28 15:17
      * @return AppEntity
      **/
    @ApiOperation(value = "微信应用服务接口")
    @PostMapping("/getApp")
    public BaseResponse<AppDTO> getApp();
}
