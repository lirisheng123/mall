package com.springboot.cloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 对生成swagger接口文档的属性的配置,例如文档大的名字
     * 指定swagger要扫描的包
     * @return
     */
    @Bean
    public Docket getUserDocket(){
        ApiInfo apiInfo=new ApiInfoBuilder()
                .title("秒杀服务")//api标题
                .description("接口描述")//api描述
                .version("1.0.0")//版本号
                .contact("DGUT")//本API负责人的联系信息
                .build();
        return new Docket(DocumentationType.SWAGGER_2)//文档类型（swagger2）
                .apiInfo(apiInfo)//设置包含在json ResourceListing响应中的api元信息
                .select()//启动用于api选择的构建器
                //注意:指定要扫描的controller的路径,要不然是不会生成接口文档的
                .apis(RequestHandlerSelectors.basePackage("com.springboot.cloud.controller"))
                .paths(PathSelectors.any())//路径过滤器（扫描所有路径）
                .build();
    }
}
