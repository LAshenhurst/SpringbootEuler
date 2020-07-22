package com.spring.Euler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    private final Tag answersTag = new Tag("Answers", "This controller calculates and returns answers to Project Euler problems.");
    private final Tag problemsTag = new Tag("Problems", "This controller returns concise descriptions of Project Euler problems for which this server contains the solution.");
    private final Tag functionsTag = new Tag("Functions", "This controller provides access to some of the functions used to calculate Project Euler answers.");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(answersTag)
                .tags(problemsTag)
                .tags(functionsTag)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
