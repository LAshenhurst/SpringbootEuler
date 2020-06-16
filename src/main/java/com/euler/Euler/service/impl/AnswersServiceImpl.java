package com.euler.Euler.service.impl;

import com.euler.Euler.domain.Response;
import com.euler.Euler.service.AnswersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    @Autowired
    private final List<String> problems;

    public Mono<Response> problemOne() {
        Integer answer = IntStream.rangeClosed(0, 1000).filter(x -> x / 3 == 0 || x / 5 == 0).sum();
        String problem = problems.get(0);
        return Mono.empty();
    }
}
