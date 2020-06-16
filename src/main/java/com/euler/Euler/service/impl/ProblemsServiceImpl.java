package com.euler.Euler.service.impl;

import com.euler.Euler.common.ApiError;
import com.euler.Euler.configuration.properties.ProblemsProperties;
import com.euler.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    private final ProblemsProperties problemsProperties;

    public Mono<String> readProblem(int index) {
        if (index > problemsProperties.getProblems().size()) {
           throw new ApiError(HttpStatus.NOT_FOUND, "Problem not listed.");
        }

        return Mono.just(problemsProperties.getProblem(index));
    }

    public Flux<String> readProblems() {
        return Flux.fromStream(problemsProperties.getProblems().stream());
    }
}
