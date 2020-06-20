package com.spring.Euler.controller;

import com.spring.Euler.domain.Answer;
import com.spring.Euler.domain.mappers.AnswerMapper;
import com.spring.Euler.helper.MathsHelper;
import com.spring.Euler.service.AnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/answers")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    private final AnswerMapper answerMapper;


    @GetMapping("/{index}")
    public Mono<Answer> getAnswer(@PathVariable Integer index) {
        return answersService.getAnswer(index);
    }

    @GetMapping("/random")
    public Mono<Answer> getRandomAnswer() {
        return answersService.getAnswer(null);
    }

    @GetMapping("/test")
    public Mono<Answer> runTestMethod() {
        return Mono.just(MathsHelper.sieveOfEratosthenes(100))
                .map(answer -> answerMapper.generate("Result of Test Method", answer.toString(), null));
    }

    @GetMapping
    public Flux<Answer> getMultipleAnswers(@RequestParam(required = false) Integer range) {
        return answersService.getAnswers(range);
    }

    @GetMapping("/all")
    public Flux<Answer> getAllAnswers() {
        return answersService.getAnswers(null);
    }
}
