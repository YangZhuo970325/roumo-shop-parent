/**
 * 文件名：AppGoods.java
 * 描述：
 **/
package com.hand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/3/4
 * @date 2020/3/4 11:33
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.hand.goods.es"})
public class AppGoods {
    public static void main(String[] args) {
        SpringApplication.run(AppGoods.class, args);
    }
}
