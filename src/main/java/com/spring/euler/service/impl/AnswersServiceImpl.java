package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiException;
import com.spring.euler.domain.Response;
import com.spring.euler.domain.Solutions;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.helper.SolutionsHelper;
import com.spring.euler.helper.TimerHelper;
import com.spring.euler.service.AnswersService;
import com.spring.euler.service.ProblemsService;
import com.spring.euler.solutions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final ProblemsService problemsService;

    public Mono<Response> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.error(new ApiException(HttpStatus.BAD_REQUEST, "Problem number must be provided.")))
                .map(SolutionsHelper::getSolution)
                .flatMap(timedSolution -> problemsService.readProblem(index).map(problem ->
                        ResponseMapper.toResponse(problem, timedSolution.getAnswer(), String.valueOf(index), true, timedSolution.getComputeTime()))
                )
                .doOnSubscribe(sub -> log.info("Calculate the solution to problem {}", index));
    }

    public Mono<Response> getAnswers(Integer min, Integer max) {
        if (min == null && max == null) { min = 1; max = Solutions.values().length; }
        else if (min == null || min <= 0 || min > Solutions.values().length) {
            String errorMessage = "Minimum value for the range must be greater than zero and less than " + Solutions.values().length + ".";
            throw new ApiException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        else if (max == null || max < min || max > Solutions.values().length) {
            String errorMessage = "Maximum value for the range must be greater than " + min + " and less than " + Solutions.values().length;
            throw new ApiException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        String task = "Calculate the solutions to problems " + min + " - " + max;
        List<Integer> indices = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());

        return Mono.just(SolutionsHelper.getSolutions(indices))
                .map(solutions -> IntStream.rangeClosed(0, indices.size() - 1)
                            .boxed()
                            .collect(Collectors.toMap(indices::get, solutions::get))
                )
                .map(answers -> ResponseMapper.toResponse(task, answers, null, false, null))
                .doOnSubscribe(sub -> log.info(task));
    }

    public Mono<Response> testMethod() {
        return Mono.just(TimerHelper.runUntilComplete(Problem52::run))
                .map(timedSolution ->
                        ResponseMapper.toResponse("Test Method", timedSolution.getAnswer(), null, false, timedSolution.getComputeTime())
                );
    }
}