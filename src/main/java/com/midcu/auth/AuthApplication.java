package com.midcu.auth;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }


    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info().title("SpringBoot API")
                                .description("sample application")
                                .version("v0.0.1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("后台管理系统文档！!!")
                        .url("https://springdoc.org/"));
    }
}
