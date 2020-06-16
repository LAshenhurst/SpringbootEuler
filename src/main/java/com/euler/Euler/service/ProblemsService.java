package com.euler.Euler.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface ProblemsService {
    Mono<String> readProblem(int problemNumber);
    Mono<List<String>> readProblems();
}