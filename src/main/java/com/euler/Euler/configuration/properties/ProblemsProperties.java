package com.euler.Euler.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotEmpty;

import java.util.Collections;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "problems")
public class ProblemsProperties {

    @NotEmpty
    private List<String> problems = Collections.emptyList();

    public String getProblem(int index) {
        return problems.get(index + 1);
    }
}