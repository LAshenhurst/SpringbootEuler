package com.spring.Euler.controller;

import com.spring.Euler.domain.Response;
import com.spring.Euler.service.ProblemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Problems"})
@RequestMapping("/v1/problems")
public class ProblemsController {
    private final ProblemsService problemsService;

    @GetMapping(path = "/{index}")
    @ApiOperation(value = "Returns the problem description for a Project Euler problem, only solved problems will appear.")
    public Mono<Response> getProblem(@Valid @PathVariable Integer index) {
        return problemsService.readProblem(index);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Returns the problem descriptions for all Project Euler problems that have been solved.")
    public Mono<Response> getProblems() {
        return problemsService.readProblems();
    }
}
