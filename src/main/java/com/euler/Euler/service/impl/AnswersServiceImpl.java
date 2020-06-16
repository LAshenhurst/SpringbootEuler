package com.euler.Euler.service.impl;

import com.euler.Euler.common.ApiError;
import com.euler.Euler.configuration.properties.ProblemsProperties;
import com.euler.Euler.domain.Answer;
import com.euler.Euler.domain.mappers.AnswerMapper;
import com.euler.Euler.service.AnswersService;
import com.euler.Euler.service.impl.solutions.FirstTenSolutions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final ProblemsProperties problemsProperties;
    private final AnswerMapper answerMapper;

    public Mono<Answer> getSolution(Integer index) {
        if (index != null) {
            return Mono.just(answerMapper.generate(problemsProperties.getProblem(index), runSolutionMethod(index)));
        } else {
            int random = ThreadLocalRandom.current().nextInt(1, problemsProperties.getProblems().size() + 1);
            return Mono.just(answerMapper.generate(problemsProperties.getProblem(random), runSolutionMethod(random)));
        }
    }

    public Flux<Answer> getSolutions() {
        return Flux.fromStream(IntStream.rangeClosed(1, problemsProperties.getProblems().size()).boxed())
                .flatMap(this::getSolution);
    }

    private Object runSolutionMethod(Integer index) {
        Object answer;
        switch (index) {
            case 1 -> answer = FirstTenSolutions.One();
            case 2 -> answer = FirstTenSolutions.Two();
            default -> answer = null;
        }
        return answer;
    }


}
