package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.helper.FilesHelper;
import com.spring.euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    private List<String> problems;

    public Mono<String> readProblem(Integer index) {
        if (index <= 0 || index > problems.size()) {
            String errorMessage = "Problem " + index + " not found. Problems 1 - " + problems.size() + " are solved.";
            throw new ApiError(HttpStatus.NOT_FOUND, errorMessage);
        }

        return Mono.just(problems.get(index - 1));
    }

    public Flux<String> readProblems() {
        return Flux.fromStream(problems.stream());
    }

    @PostConstruct
    private void init() {
        File file = FilesHelper.getResourceFile("static/problemsList.txt");
        problems = FilesHelper.readAllLines(file);
    }
}
