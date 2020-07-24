package com.spring.euler.service;

import com.spring.euler.domain.Response;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProblemsService {
    String getProblem(Integer index);
    List<String> getAllProblems();

    Mono<Response> readProblem(Integer index);
    Mono<Response> readProblems();
}