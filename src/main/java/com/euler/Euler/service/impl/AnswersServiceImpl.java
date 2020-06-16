package com.euler.Euler.service.impl;

import com.euler.Euler.domain.Answer;
import com.euler.Euler.domain.mappers.AnswerMapper;
import com.euler.Euler.service.AnswersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    //private final List<String> problems;
    //private final AnswerMapper answerMapper;

    public Mono<Integer> problemOne() {
        return Mono.just(IntStream.rangeClosed(0, 1000).filter(x -> x / 3 == 0 || x / 5 == 0).sum());
    }
}
