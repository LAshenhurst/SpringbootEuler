package com.spring.euler.service;

import com.spring.euler.domain.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Response> getAnswer(Integer index);
    Flux<Response> getAnswers(Integer range);
}
