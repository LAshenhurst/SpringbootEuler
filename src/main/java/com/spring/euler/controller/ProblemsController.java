package com.spring.euler.controller;

import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.service.ProblemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        return problemsService.readProblem(index)
                .map(problem -> ResponseMapper.toResponse(
                        "Return the description for problem " + index,
                        problem,
                        null,
                        false,
                        null
                ));
    }

    @GetMapping
    @ApiOperation(value = "Returns the problem descriptions for all Project Euler problems that have been solved.")
    public Mono<Response> getProblems() {
        return problemsService.readProblems()
                .collectList()
                .map(problemsList -> ResponseMapper.toResponse(
                        "Return the descriptions for all solved problems",
                        problemsList,
                        null,
                        false,
                        null
                ));
    }
}
