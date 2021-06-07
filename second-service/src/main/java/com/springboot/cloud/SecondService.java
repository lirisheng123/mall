package com.springboot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients
public class SecondService
{
    public static void main(String[] args) {

        SpringApplication.run(SecondService.class, args);
    }
}
