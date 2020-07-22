package com.spring.Euler.service;

import com.spring.Euler.domain.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Response> getAnswer(Integer index);
    Flux<Response> getAnswers(Integer range);
}
