package com.spring.Euler.service;

import com.spring.Euler.domain.Response;
import reactor.core.publisher.Mono;

public interface ProblemsService {
    Mono<Response> readProblem(int problemNumber);
    Mono<Response> readProblems();
}