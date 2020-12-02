/**
 * 文件名：WeiXinServiceImpl.java
 * 描述：
 **/
package com.hand.weixin.service.impl;

import com.hand.base.BaseApiService;
import com.hand.base.BaseResponse;
import com.hand.weixin.input.dto.AppDTO;
import com.hand.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 15:34
 */
@RestController
public class WeiXinServiceImpl extends BaseApiService<AppDTO> implements WeiXinService{

    @Value("${roumo.weixin.name}")
    private String name;

    @Override
    public BaseResponse<AppDTO> getApp() {
        return setResultSuccess(new AppDTO("1", name));
    }
}
