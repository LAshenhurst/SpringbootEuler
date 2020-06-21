package com.spring.Euler.service.impl;

import com.spring.Euler.common.ApiError;
import com.spring.Euler.configuration.properties.ProblemsProperties;
import com.spring.Euler.domain.Answer;
import com.spring.Euler.domain.mappers.AnswerMapper;
import com.spring.Euler.service.AnswersService;
import com.spring.Euler.service.impl.solutions.Solutions;
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
    private final Solutions solutions;
    private final AnswerMapper answerMapper;
    private final ProblemsProperties problemsProperties;

    public Mono<Answer> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.just(ThreadLocalRandom.current().nextInt(1, problemsProperties.getProblems().size() - 1)))
                .map(val -> {
                    if (val <= problemsProperties.getProblems().size()) {
                        return answerMapper.generate(problemsProperties.getProblem(val), getSolution(val), val);
                    } else { throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found"); }
                });
    }

    public Flux<Answer> getAnswers(Integer range) {
        return Mono.justOrEmpty(range)
                .switchIfEmpty(Mono.just(problemsProperties.getProblems().size()))
                .flatMapMany(finalRange -> {
                    if (finalRange <= problemsProperties.getProblems().size()) {
                        return Flux.fromStream(IntStream.rangeClosed(1, finalRange).boxed());
                    } else { throw new ApiError(HttpStatus.BAD_REQUEST, "range value too high"); }
                })
                .flatMap(this::getAnswer);
    }

    private String getSolution(Integer index) {
        Object answer;
        switch (index) {
            case 1 -> answer = solutions.One();
            case 2 -> answer = solutions.Two();
            case 3 -> answer = solutions.Three();
            case 4 -> answer = solutions.Four();
            case 5 -> answer = solutions.Five();
            case 6 -> answer = solutions.Six();
            case 7 -> answer = solutions.Seven();
            case 8 -> answer = solutions.Eight();
            case 9 -> answer = solutions.Nine();
            case 10 -> answer = solutions.Ten();
            case 11 -> answer = solutions.Eleven();
            case 12 -> answer = solutions.Twelve();
            case 13 -> answer = solutions.Thirteen();
            case 14 -> answer = solutions.Fourteen();
            case 15 -> answer = solutions.Fifteen();
            case 16 -> answer = solutions.Sixteen();
            case 17 -> answer = solutions.Seventeen();
            default -> throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found");
        }
        return answer.toString();
    }
}