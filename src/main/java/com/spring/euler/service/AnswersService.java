package com.spring.euler.service;

import com.spring.euler.domain.Response;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Response> getAnswer(Integer index);
    Mono<Response> getRandomAnswer();
    Mono<Response> getAnswers(Integer min, Integer max, Boolean all);

    Mono<Response> testMethod();
}
