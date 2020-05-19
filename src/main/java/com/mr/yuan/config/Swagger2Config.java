package com.mr.yuan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
     public Docket createRestApi() {
         return new Docket(DocumentationType.SWAGGER_2)
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.basePackage("com.mr.yuan.web"))
                 .paths(PathSelectors.any())
                 .build();
     }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于Netty4.1.29进行简单应用")
                .description("基于Netty4.1.29进行简单应用；拥有客户端接入监听、数据流缓冲对象池、数据下行等功能")
                .termsOfServiceUrl("")
                .contact("Mr.yuan")
                .version("1.0")
                .build();
    }
}
