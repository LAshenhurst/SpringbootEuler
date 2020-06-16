package com.spring.Euler.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "static")
public class ProblemsProperties {

    @NotEmpty
    private List<String> problems = new ArrayList<>();

    public String getProblem(int index) {
        return problems.get(index - 1);
    }
}