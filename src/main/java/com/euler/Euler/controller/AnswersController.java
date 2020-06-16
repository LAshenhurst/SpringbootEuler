package com.euler.Euler.controller;

import com.euler.Euler.domain.Answer;
import com.euler.Euler.service.AnswersService;
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


    @GetMapping("/{index}")
    public Mono<Answer> getFirstAnswer(@PathVariable Integer index) {
        return answersService.getSolution(index);
    }

    @GetMapping("/random")
    public Mono<Answer> getRandomAnswer() {
        return answersService.getSolution(null);
    }

    @GetMapping
    public Flux<Answer> getAllAnswers() {
        return answersService.getSolutions();
    }
}
