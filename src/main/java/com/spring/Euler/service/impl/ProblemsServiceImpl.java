package com.spring.Euler.service.impl;

import com.spring.Euler.common.ApiError;
import com.spring.Euler.configuration.properties.ProblemsProperties;
import com.spring.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    private final ProblemsProperties problemsProperties;

    public Mono<String> readProblem(int index) {
        if (index > problemsProperties.getProblems().size()) {
           throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found.");
        }

        return Mono.just(problemsProperties.getProblem(index));
    }

    public Flux<String> readProblems() {
        return Flux.fromStream(problemsProperties.getProblems().stream());
    }
}
