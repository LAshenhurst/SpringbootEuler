package com.spring.Euler.service;

import com.spring.Euler.domain.Answer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Answer> getAnswer(Integer index);
    Flux<Answer> getAnswers(Integer range);
}
