package com.spring.Euler.service.impl;

import com.spring.Euler.common.ApiError;
import com.spring.Euler.configuration.properties.ProblemsProperties;
import com.spring.Euler.domain.Answer;
import com.spring.Euler.domain.mappers.AnswerMapper;
import com.spring.Euler.service.AnswersService;
import com.spring.Euler.service.impl.solutions.FirstTenSolutions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final AnswerMapper answerMapper;
    private final ProblemsProperties problemsProperties;

    public Mono<Answer> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.just(ThreadLocalRandom.current().nextInt(1, problemsProperties.getProblems().size() + 1)))
                .map(val -> {
                    if (val <= problemsProperties.getProblems().size()) {
                        return answerMapper.generate(problemsProperties.getProblem(val), getSolution(val), val);
                    } else { throw new ApiError(HttpStatus.NOT_FOUND, "Problem not listed."); }
                });
    }

    public Flux<Answer> getAnswers(Integer range) {
        return Mono.justOrEmpty(range)
                .switchIfEmpty(Mono.just(problemsProperties.getProblems().size()))
                .flatMapMany(finalRange -> {
                    if (finalRange <= problemsProperties.getProblems().size()) {
                        return Flux.fromStream(IntStream.rangeClosed(1, finalRange).boxed());
                    } else { throw new ApiError(HttpStatus.BAD_REQUEST, "range value too high."); }
                })
                .flatMap(this::getAnswer);
    }

    private String getSolution(Integer index) {
        Object answer;
        switch (index) {
            case 1 -> answer = FirstTenSolutions.One();
            case 2 -> answer = FirstTenSolutions.Two();
            case 3 -> answer = FirstTenSolutions.Three();
            case 4 -> answer = FirstTenSolutions.Four();
            case 5 -> answer = FirstTenSolutions.Five();
            default -> throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found.");
        }
        return answer.toString();
    }
}