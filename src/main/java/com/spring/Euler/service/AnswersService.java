package com.spring.Euler.service;

import com.spring.Euler.domain.Answer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Answer> getSolution(Integer index);
    Flux<Answer> getSolutions();
}
