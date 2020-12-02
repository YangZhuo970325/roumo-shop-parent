/**
 * 文件名：AppDTO.java
 * 描述：
 **/
package com.hand.weixin.input.dto;

import lombok.Data;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/1
 * @date 2020/2/1 17:02
 */
@Data
public class AppDTO {
    /**
     * appid
     */
    private String appId;
    /**
     * 应用名称
     */
    private String appName;

    public AppDTO() {

    }

    public AppDTO(String appId, String appName) {
        super();
        this.appId = appId;
        this.appName = appName;
    }

}
