package com.euler.Euler.service;

import com.euler.Euler.domain.Response;
import reactor.core.publisher.Mono;

public interface AnswersService {
    Mono<Response> problemOne();
}
