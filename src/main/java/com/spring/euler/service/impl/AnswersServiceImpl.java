package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.configuration.properties.ProblemsProperties;
import com.spring.euler.domain.Response;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.service.AnswersService;
import com.spring.euler.service.impl.solutions.FirstSolutions;
import com.spring.euler.service.impl.solutions.SecondSolutions;
import com.spring.euler.service.impl.solutions.ThirdSolutions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswersServiceImpl implements AnswersService {
    private final ResponseMapper responseMapper;
    private final ProblemsProperties problemsProperties;

    public Mono<Response> getAnswer(Integer index) {
        return Mono.justOrEmpty(index)
                .switchIfEmpty(Mono.error(new ApiError(HttpStatus.BAD_REQUEST, "Problem number must be provided.")))
                .map(val -> responseMapper.generate(problemsProperties.getProblem(val), timedGetSolution(val), String.valueOf(val), true));
    }

    public Flux<Response> getAnswers(Integer range) {
        return Mono.justOrEmpty(range)
                .switchIfEmpty(Mono.just(problemsProperties.getAllProblems().size()))
                .flatMapMany(finalRange -> {
                    if (finalRange <= problemsProperties.getAllProblems().size()) {
                        return Flux.fromStream(IntStream.rangeClosed(1, finalRange).boxed());
                    } else { throw new ApiError(HttpStatus.BAD_REQUEST, "range value too high"); }
                })
                .flatMap(this::getAnswer);
    }

    private Object getSolution(Integer index) {
        Object answer;

        if (index <= 10) { answer = FirstSolutions.getAnswer(index); }
        else if (index <= 20) { answer = SecondSolutions.getAnswer(index); }
        else if (index <= 30) { answer = ThirdSolutions.getAnswer(index);}
        else {
            String errorMessage = "Attempted to solve unlisted problem: " + index;
            throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }

        return answer;
    }

    private Object timedGetSolution(Integer index) {
        Object answer;

        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Object> getSolutionTask = () -> getSolution(index);
        Future<Object> future = executor.submit(getSolutionTask);

        try { answer = future.get(30, TimeUnit.SECONDS); }
        catch (TimeoutException ex) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Solution method timed out."); }
        catch (InterruptedException | ExecutionException ex) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()); }

        return answer;
    }
}