package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.helper.FilesHelper;
import com.spring.euler.service.ProblemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemsServiceImpl implements ProblemsService {
    private List<String> problems;
    private final ResponseMapper responseMapper;

    public Mono<Response> readProblem(Integer index) {
        if (index <= 0 || index > problems.size()) {
            String errorMessage = "Problem " + index + " not found. Problems 1 - " + problems.size() + " are solved.";
            throw new ApiError(HttpStatus.NOT_FOUND, errorMessage);
        }

        return Mono.just(responseMapper.generate(
                "Retrieve the description for problem " + index,
                problems.get(index - 1),
                null,
                false,
                null
        ));
    }

    public Mono<Response> readProblems() {
        return Mono.just(responseMapper.generate(
                "Retrieve all listed Project Euler project descriptions.",
                problems,
                null,
                false,
                null
        ));
    }

    public String getProblem(Integer index) {
        if (index < 0) { throw new ApiError(HttpStatus.BAD_REQUEST, "Problem number must be greater than zero."); }
        else if (index > problems.size()) {
            String errorMessage = "Problem " + index + " not found. Problems listed are 1 - " + problems.size();
            throw new ApiError(HttpStatus.NOT_FOUND, errorMessage);
        }
        else { return problems.get(index - 1); }
    }

    public List<String> getAllProblems() { return problems; }

    @PostConstruct
    private void init() {
        File file = FilesHelper.getResourceFile("static/problemsList.txt");
        problems = FilesHelper.readAllLines(file);
    }
}
