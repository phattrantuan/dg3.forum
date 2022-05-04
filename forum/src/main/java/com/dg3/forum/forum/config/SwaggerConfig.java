package com.dg3.forum.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
//Sau khi Docket bean được khởi tạo, phương thức select () của nó trả về một ApiSelectorBuilder instance, cung cấp cơ chế để kiểm soát các API bởi Swagger.
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.example.demohung.Controller"))
                //.any thì chọn hiển thị ở bên swagger tất cả API của controller.
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Hiển thị các mục
     * @return
     */
    private ApiInfo apiInfo()
    //cung cấp các thông tin về API Information
    {
        return new ApiInfo(
                "Forum Covid-19 of DG3"
                ,"List API project Forum Covid-19"
                ,"ver 1.0"
                , null
                , null
                ,null
                , null
                , Collections.emptyList()
        );
    }
}
