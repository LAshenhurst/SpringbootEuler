package com.spring.Euler.configuration.properties;

import com.spring.Euler.helper.FilesHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ProblemsProperties {
    private List<String> problems = new ArrayList<>();

    public String getProblem(Integer index) {
        return problems.get(index - 1);
    }

    public List<String> getAllProblems() { return problems; }

    @PostConstruct
    private void init() {
        File file = FilesHelper.getResourceFile("static/problemsList.txt");
        problems = FilesHelper.readAllLines(file);
    }
}
