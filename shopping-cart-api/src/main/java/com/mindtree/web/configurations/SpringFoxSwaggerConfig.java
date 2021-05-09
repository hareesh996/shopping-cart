package com.mindtree.web.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration()
@EnableSwagger2()
public class SpringFoxSwaggerConfig {

    @Bean
    public Docket shoppingCartAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mindtree.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Shopping Cart API")
                .description("Shopping Cart API")
                .version("v1")
                .contact(new Contact("Hareesh", "https://github.com/hareesh996", "hareesh996@gmail.com"))
                .license("MIT")
                .build();
    }

}
