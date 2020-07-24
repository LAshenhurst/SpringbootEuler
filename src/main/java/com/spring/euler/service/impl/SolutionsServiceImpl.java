package com.spring.euler.service.impl;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Solutions;
import com.spring.euler.service.SolutionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolutionsServiceImpl implements SolutionsService {
    private final List<Solutions> solutions = List.of(Solutions.values());

    public Object getSolution(Integer index) {
        Object answer;

        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Object> getSolutionTask = () -> {
            log.info("Calculating solution to problem {}", index);
            return solutions.get(index - 1).run();
        };
        Future<Object> future = executor.submit(getSolutionTask);

        try { answer = future.get(30, TimeUnit.SECONDS); }
        catch (TimeoutException ex) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Solution method timed out."); }
        catch (InterruptedException | ExecutionException ex) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()); }

        return answer;
    }
}
