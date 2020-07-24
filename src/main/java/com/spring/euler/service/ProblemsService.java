package com.spring.euler.service;

import com.spring.euler.domain.Response;
import reactor.core.publisher.Mono;

public interface ProblemsService {
    Mono<Response> readProblem(int problemNumber);
    Mono<Response> readProblems();
}