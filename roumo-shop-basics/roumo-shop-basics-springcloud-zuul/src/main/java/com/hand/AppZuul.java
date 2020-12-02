/**
 * 文件名：AppZuul.java
 * 描述：
 **/
package com.hand;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 微服务网关入口
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2020/1/28
 * @date 2020/1/28 17:08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
public class AppZuul {

    //获取config
    @ApolloConfig
    private Config config;

    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class, args);
    }

    //添加文档来源
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider{

        //访问swagger-ui页面每次都会访问get方法
        @SuppressWarnings("rawtypes")
        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            //网关使用服务别名获取远程服务的SwaggerApi
            return resources();
        }

        /**
          * @desc 从阿波罗服务器中获取resources
          * @param 
          * @date 2020/1/30 15:22
          * @return java.util.List<springfox.documentation.swagger.web.SwaggerResource>
          **/
        private List<SwaggerResource> resources(){

            List resouces = new ArrayList<>();
            //app-hand-order
            //网关使用服务别名获取远程服务的SwaggerApi
            String swaggerDocJson = swaggerDocument();
            JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
            for(Object object : jsonArray){
                JSONObject jsonObject = (JSONObject) object;
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                String version = jsonObject.getString("version");
                resouces.add(swaggerResource(name, location, version));
            }
            return resouces;
        }

        /**
          * @desc 获取swaggerDocument配置
          * @param
          * @date 2020/1/30 15:14
          * @return java.lang.String
          **/
        private String swaggerDocument(){
            String property = config.getProperty("hand.zuul.swagger.document","");
            return property;
        }

        private SwaggerResource swaggerResource(String name, String location, String version){
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
}
