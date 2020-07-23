package com.spring.Euler.service.impl;

import com.spring.Euler.common.exception.ApiError;
import com.spring.Euler.configuration.properties.ProblemsProperties;
import com.spring.Euler.domain.Response;
import com.spring.Euler.domain.mappers.ResponseMapper;
import com.spring.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    private final ResponseMapper responseMapper;
    private final ProblemsProperties problemsProperties;

    public Mono<Response> readProblem(int index) {
        if (index > problemsProperties.getAllProblems().size()) {
           throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found.");
        }

        return Mono.just(responseMapper.generate(
                "Retrieve the description for problem " + index,
                problemsProperties.getProblem(index),
                null,
                false
        ));
    }

    public Mono<Response> readProblems() {
        return Mono.just(responseMapper.generate(
                "Retrieve all listed Project Euler project descriptions.",
                problemsProperties.getAllProblems(),
                null,
                false
        ));
    }
}
