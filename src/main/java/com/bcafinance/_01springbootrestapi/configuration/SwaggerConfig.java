package com.bcafinance._01springbootrestapi.configuration;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 07/12/2022 09:49
Last modified on 09:49
Version 1.0
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).
                select().
                apis(RequestHandlerSelectors.basePackage("com.bcafinance._01springbootrestapi.controllers")).
                paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "SpringBootRestAPI",//title
                "Springboot bootcamp BCAF BATCH 1",//descriptions
                "v1.0",//version
                "Term Of Services URL is On Progress",//termsOfServiceUrl
                new Contact("Widia Anggraeni","localhost:8080/api/v1","widia-a@gmail.com"),//Contact Name
                "Springboot Open Source License",//license
                "https://spring.io/",//licenseUrl
                Collections.emptyList()
        );
        return apiInfo;
    }
}
