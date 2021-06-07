package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class RecommendService
{
    public static void main(String[] args) {
        SpringApplication.run(RecommendService.class, args);
    }
}
