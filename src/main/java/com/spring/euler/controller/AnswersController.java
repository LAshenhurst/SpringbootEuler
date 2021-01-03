package com.spring.euler.controller;

import com.spring.euler.domain.Response;
import com.spring.euler.service.AnswersService;
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

    @GetMapping("/{index}")
    @ApiOperation(value = "Returns the answer to a solved problem.")
    public Mono<Response> getAnswer(@Valid @PathVariable Integer index) {
        return answersService.getAnswer(index);
    }

    @GetMapping
    @ApiOperation(value = "Returns a range of answers, or all answers if no min and max are provided. Some methods take several seconds.")
    public Mono<Response> getMultipleAnswers(@Valid @RequestParam(required = false) Integer min,
                                             @Valid @RequestParam(required = false) Integer max) {
        return answersService.getAnswers(min, max);
    }
}