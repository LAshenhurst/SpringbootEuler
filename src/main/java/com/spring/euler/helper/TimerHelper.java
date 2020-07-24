package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.TimedSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public abstract class TimerHelper {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static <T> TimedSolution run(Callable<T> method) {
        Future<T> future = executor.submit(method);
        return processMethod(future);
    }

    public static <T> List<TimedSolution> runMany(List<Callable<T>> methods) {
        return methods.stream()
                .map(executor::submit)
                .map(TimerHelper::processMethod)
                .collect(Collectors.toList());
    }

    private static <T> TimedSolution processMethod(Future<T> future) {
        T answer;
        String computeTime;

        try {
            StopWatch watch = new StopWatch();
            watch.start();
            answer = future.get(30, TimeUnit.SECONDS);
            watch.stop();
            computeTime = watch.getTotalTimeMillis() == 0 ? "< 1 ms" : watch.getTotalTimeMillis() + " ms";
        }
        catch (TimeoutException ex) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Solution method timed out."); }
        catch (InterruptedException | ExecutionException ex) {
            if (ex.getCause() instanceof ApiError) { throw new ApiError(((ApiError) ex.getCause()).getStatus(), ex.getCause().getMessage()); }
            else { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()); }
        }

        return TimedSolution.builder()
                .answer(answer)
                .computeTime(computeTime)
                .build();
    }
}
