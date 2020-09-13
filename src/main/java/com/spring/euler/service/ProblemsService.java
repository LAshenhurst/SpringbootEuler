package com.spring.euler.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProblemsService {
    Mono<String> readProblem(Integer index);
    Flux<String> readProblems();
}