package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.service.AnswersService;
import com.spring.euler.service.ProblemsService;
import com.spring.euler.service.SolutionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final ResponseMapper responseMapper;
    private final ProblemsService problemsService;
    private final SolutionsService solutionsService;

    public Mono<Response> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.error(new ApiError(HttpStatus.BAD_REQUEST, "Problem number must be provided.")))
                .map(val -> responseMapper.generate(problemsService.getProblem(val), solutionsService.getSolution(val), String.valueOf(val), true));
    }

    public Mono<Response> getRandomAnswer() {
        Integer random = (int) (Math.random() * (problemsService.getAllProblems().size() - 1));
        return getAnswer(random);
    }

    public Mono<Response> getAnswers(Integer min, Integer max, Boolean all) {
        if (all) { min = 1; max = problemsService.getAllProblems().size(); }
        if (min == null || min <= 0) { throw new ApiError(HttpStatus.BAD_REQUEST, "Minimum value for the range must be greater than zero."); }
        if (max == null || max < min || max > problemsService.getAllProblems().size()) {
            String errorMessage = "Maximum value for the range must be greater than " + min + " and less than " + problemsService.getAllProblems().size();
            throw new ApiError(HttpStatus.BAD_REQUEST, errorMessage);
        }
        Map<Integer, Object> answers = new HashMap<>();
        IntStream.rangeClosed(min, max).boxed().forEach(x -> answers.put(x, solutionsService.getSolution(x)));

        return Mono.just(responseMapper.generate(
                "Generate the solutions to problems " + min + " - " + max,
                answers,
                null,
                false
        ));
    }
}