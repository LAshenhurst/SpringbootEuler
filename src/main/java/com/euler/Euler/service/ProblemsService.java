package com.euler.Euler.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProblemsService {
    Mono<String> readProblem(int problemNumber);
    Flux<String> readProblems();
}