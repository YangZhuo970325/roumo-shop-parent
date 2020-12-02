/**
 * 文件名：AppPayWeb.java
 * 描述：
 **/
package com.hand.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/2/22
 * @date 2020/2/22 10:44
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class AppPayWeb {
    public static void main(String[] args) {
        SpringApplication.run(AppPayWeb.class, args);
    }
}
