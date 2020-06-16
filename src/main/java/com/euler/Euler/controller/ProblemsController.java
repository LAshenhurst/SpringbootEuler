package com.euler.Euler.controller;

import com.euler.Euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/problems")
public class ProblemsController {
    //@Autowired
    //private final ProblemsService problemsService;

    @GetMapping(path = "/{problemNumber}")
    public Mono<String> getProblem(@Valid @RequestParam Integer problemNumber) {
        return null;
        //return problemsService.readProblem(problemNumber + 1);
    }

    @GetMapping
    public Mono<List<String>> getProblems() {
        return null;
        //return problemsService.readProblems();
    }
}
