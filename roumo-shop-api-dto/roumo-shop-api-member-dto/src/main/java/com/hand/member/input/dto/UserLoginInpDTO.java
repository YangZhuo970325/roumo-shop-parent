/**
 * 文件名：UserLoginInpDTO.java
 * 描述：
 **/
package com.hand.member.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc 用户登录请求参数
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/2
 * @date 2020/2/2 13:58
 */

/**
  *
  **/
@Data
@ApiModel(value = "用户登录参数")
public class UserLoginInpDTO {
    /**
      * 手机号码
      **/
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
      * 密码
      **/
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 登录类型 PC、Android、IOS
     **/
    @ApiModelProperty(value = "登录类型")
    private String loginType;

    /**
     * 设备信息
     **/
    @ApiModelProperty(value = "设备信息")
    private String deviceInfo;
}
