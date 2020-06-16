package com.euler.Euler.service.impl;

import com.euler.Euler.common.ApiError;
import com.euler.Euler.configuration.properties.ProblemsProperties;
import com.euler.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    @Autowired
    private final ProblemsProperties problemsProperties;

    public Mono<String> readProblem(int problemNumber) {
        //if (problemNumber > problems.size()) {
       //     throw new ApiError(HttpStatus.BAD_REQUEST, "Problem not listed.");
       // }
       // return Mono.just(problems.get(problemNumber));
        return null;
    }

    public Mono<List<String>> readProblems() {
        return null;
    }
}
