package com.spring.euler.configuration.properties;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.helper.FilesHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ProblemsProperties {
    private List<String> problems = new ArrayList<>();

    public String getProblem(Integer index) {
        if (index > problems.size()) {
            String errorMessage = "Problem " + index + " not found. Problems listed are 1 - " + problems.size();
            throw new ApiError(HttpStatus.NOT_FOUND, errorMessage);
        }
        else { return problems.get(index - 1); }
    }

    public List<String> getAllProblems() { return problems; }

    @PostConstruct
    private void init() {
        File file = FilesHelper.getResourceFile("static/problemsList.txt");
        problems = FilesHelper.readAllLines(file);
    }
}
