package com.spring.Euler.controller;

import com.spring.Euler.domain.Answer;
import com.spring.Euler.domain.mappers.AnswerMapper;
import com.spring.Euler.helper.MathsHelper;
import com.spring.Euler.service.AnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/answers")
@RequiredArgsConstructor
public class AnswersController {
    private final AnswersService answersService;
    private final AnswerMapper answerMapper;


    @GetMapping("/{index}")
    public Mono<Answer> getFirstAnswer(@PathVariable Integer index) {
        return answersService.getSolution(index);
    }

    @GetMapping("/random")
    public Mono<Answer> getRandomAnswer() {
        return answersService.getSolution(null);
    }

    @GetMapping("/test")
    public Mono<Answer> runTestMethod() {
        return Mono.just(MathsHelper.isPrime(91))
                .map(answer -> answerMapper.generate("Result of Test Method", answer, null));
    }

    @GetMapping("/all")
    public Flux<Answer> getAllAnswers() {
        return answersService.getSolutions();
    }
}
