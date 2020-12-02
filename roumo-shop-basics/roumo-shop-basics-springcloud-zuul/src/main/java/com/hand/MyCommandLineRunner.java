/**
 * 文件名：MyCommandLineRunner.java
 * 描述：
 **/
package com.hand;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/30
 * @date 2020/1/30 16:06
 */
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner{

    @ApolloConfig
    private Config config;

    @Override
    public void run(String... args) throws Exception {

        log.info("###################MyCommandLineRunner启动#####################");

        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                log.info("######分布式配置中心监听######", configChangeEvent.changedKeys().toString());
            }
        });
    }
}
