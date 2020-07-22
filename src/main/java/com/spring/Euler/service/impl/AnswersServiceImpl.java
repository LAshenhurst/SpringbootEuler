package com.spring.Euler.service.impl;

import com.spring.Euler.common.exception.ApiError;
import com.spring.Euler.configuration.properties.ProblemsProperties;
import com.spring.Euler.domain.Response;
import com.spring.Euler.domain.mappers.ResponseMapper;
import com.spring.Euler.service.AnswersService;
import com.spring.Euler.service.impl.solutions.FirstSolutions;
import com.spring.Euler.service.impl.solutions.SecondSolutions;
import com.spring.Euler.service.impl.solutions.ThirdSolutions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final ResponseMapper responseMapper;
    private final ProblemsProperties problemsProperties;

    public Mono<Response> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.error(new ApiError(HttpStatus.BAD_REQUEST, "Problem number must be provided.")))
                .map(val -> {
                    if (val <= problemsProperties.getProblems().size()) {
                        return responseMapper.generate(problemsProperties.getProblem(val), getSolution(val), String.valueOf(val), true);
                    } else { throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found"); }
                });
    }

    public Flux<Response> getAnswers(Integer range) {
        return Mono.justOrEmpty(range)
                .switchIfEmpty(Mono.just(problemsProperties.getProblems().size()))
                .flatMapMany(finalRange -> {
                    if (finalRange <= problemsProperties.getProblems().size()) {
                        return Flux.fromStream(IntStream.rangeClosed(1, finalRange).boxed());
                    } else { throw new ApiError(HttpStatus.BAD_REQUEST, "range value too high"); }
                })
                .flatMap(this::getAnswer);
    }

    private Object getSolution(Integer index) {
        Object answer;

        if (index <= 10) { answer = FirstSolutions.getAnswer(index); }
        else if (index <= 20) { answer = SecondSolutions.getAnswer(index); }
        else if (index <= 30) { answer = ThirdSolutions.getAnswer(index);}
        else { throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found"); }

        return answer;
    }
}