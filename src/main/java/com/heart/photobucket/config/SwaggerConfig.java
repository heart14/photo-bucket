package com.heart.photobucket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * About:
 * Other:
 * Created: Administrator on 2022/3/10 12:25.
 * Editored:
 */
@Configuration
public class SwaggerConfig {

    //swagger接口文档地址
    //http://localhost:9999/swagger-ui/index.html

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.heart.photobucket.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("图床 Api Documentation")
                .description("图床 Api Description")
                .contact(new Contact("heart", "sadli.xyz", "heart@sadli.xyz"))
                .version("v1.0")
                .build();
    }
}
