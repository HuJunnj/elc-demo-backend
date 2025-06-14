package com.example.demo.demos.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("电商平台 API 文档")
                        .description("基于 Spring Boot 3 的电商系统后端接口")
                        .version("v1.0"));
    }

    // 如果你想给某个分组添加文档（可选）
    @Bean
    public GroupedOpenApi backendApi() {
        return GroupedOpenApi.builder()
                .group("backend")
                .pathsToMatch("/products/**", "/orders/**")
                .build();
    }
}
