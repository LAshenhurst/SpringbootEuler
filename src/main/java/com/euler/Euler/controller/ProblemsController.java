package com.euler.Euler.controller;

import com.euler.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/problems")
public class ProblemsController {
    private final ProblemsService problemsService;

    @GetMapping(path = "/{index}")
    public Mono<String> getProblem(@Valid @PathVariable Integer index) {
        return problemsService.readProblem(index);
    }

    @GetMapping
    public Flux<String> getProblems() {
        return problemsService.readProblems();
    }
}
