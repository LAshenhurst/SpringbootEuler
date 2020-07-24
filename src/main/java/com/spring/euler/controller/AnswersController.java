package com.spring.euler.controller;

import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.service.AnswersService;
import com.spring.euler.solutions.Problem29;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        return answersService.getRandomAnswer();
    }

    @GetMapping("/test")
    @ApiOperation(value = "Runs a specified method for testing purposes.")
    public Mono<Response> runTestMethod() {
        return Mono.just(Problem29.run())
                .map(answer -> responseMapper.generate("Result of Test Method", answer, null, false));
    }

    @GetMapping("/range")
    @ApiOperation(value = "Returns a range of answers. Please be aware some methods take several seconds.")
    public Mono<Response> getMultipleAnswers(@Valid @RequestParam Integer min,
                                             @Valid @RequestParam Integer max) {
        return answersService.getAnswers(min, max, false);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Returns all answers. Please be aware some methods take several seconds.")
    public Mono<Response> getAllAnswers() {
        return answersService.getAnswers(null, null, true);
    }
}
