package com.spring.Euler.controller;

import com.spring.Euler.domain.Response;
import com.spring.Euler.domain.mappers.ResponseMapper;
import com.spring.Euler.service.AnswersService;
import com.spring.Euler.service.impl.solutions.ThirdSolutions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Api(tags = {"Answers"})
@RequiredArgsConstructor
@RequestMapping("/v1/answers")
public class AnswersController {
    private final AnswersService answersService;
    private final ResponseMapper responseMapper;


    @GetMapping("/{index}")
    @ApiOperation(value = "Returns the answer to a solved problem.")
    public Mono<Response> getAnswer(@Valid @PathVariable Integer index) {
        return answersService.getAnswer(index);
    }

    @GetMapping("/random")
    @ApiOperation(value = "Returns the answer to a random Project Euler problem.")
    public Mono<Response> getRandomAnswer() {
        return answersService.getAnswer(null);
    }

    @GetMapping("/test")
    @ApiOperation(value = "Runs a specified method for testing purposes.")
    public Mono<Response> runTestMethod() {
        return Mono.just(ThirdSolutions.getAnswer(26))
                .map(answer -> responseMapper.generate("Result of Test Method", answer, null, false));
    }

    @GetMapping
    @ApiOperation(value = "Returns the first x answers, if no value is specified then all answers are returned.")
    public Flux<Response> getMultipleAnswers(@Valid @RequestParam(required = false) Integer range) {
        return answersService.getAnswers(range);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Returns all answers.")
    public Flux<Response> getAllAnswers() {
        return answersService.getAnswers(null);
    }
}
