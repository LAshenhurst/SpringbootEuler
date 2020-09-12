package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.helper.SolutionsHelper;
import com.spring.euler.service.AnswersService;
import com.spring.euler.service.ProblemsService;
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
    private final ResponseMapper responseMapper;
    private final ProblemsService problemsService;

    public Mono<Response> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.error(new ApiError(HttpStatus.BAD_REQUEST, "Problem number must be provided.")))
                .map(SolutionsHelper::getSolution)
                .map(timedSolution -> responseMapper.generate(problemsService.getProblem(index), timedSolution.getAnswer(), String.valueOf(index), true, timedSolution.getComputeTime()))
                .doOnSubscribe(sub -> log.info("Calculate the solution to problem {}", index));
    }

    public Mono<Response> getRandomAnswer() {
        Integer random = (int) (Math.random() * (problemsService.getAllProblems().size() - 1));
        return Mono.just(random).flatMap(this::getAnswer);
    }

    public Mono<Response> getAnswers(Integer min, Integer max, Boolean all) {
        if (all) { min = 1; max = problemsService.getAllProblems().size(); }
        else if (min == null || min <= 0) { throw new ApiError(HttpStatus.BAD_REQUEST, "Minimum value for the range must be greater than zero."); }
        else if (max == null || max < min || max > problemsService.getAllProblems().size()) {
            String errorMessage = "Maximum value for the range must be greater than " + min + " and less than " + problemsService.getAllProblems().size();
            throw new ApiError(HttpStatus.BAD_REQUEST, errorMessage);
        }

        String task = "Calculate the solutions to problems " + min + " - " + max;
        List<Integer> indices = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());

        return Mono.just(SolutionsHelper.getSolutions(indices))
                .map(solutions -> IntStream.rangeClosed(0, indices.size() - 1)
                            .boxed()
                            .collect(Collectors.toMap(indices::get, solutions::get)))
                .map(answers -> responseMapper.generate(task, answers, null, false, null))
                .doOnSubscribe(sub -> log.info(task));
    }
}