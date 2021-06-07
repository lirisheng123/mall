package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class UserService
{
    public static void main( String[] args )
    {
        //添加对request get  中的数组的解析
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","[]");
        SpringApplication.run(UserService.class, args);
    }
}
