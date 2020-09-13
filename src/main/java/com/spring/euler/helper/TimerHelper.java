package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.TimedSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

@Slf4j
public abstract class TimerHelper {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static <T> TimedSolution run(Callable<T> method, Boolean timeout) { return processMethod(executor.submit(method), timeout); }

    private static <T> TimedSolution processMethod(Future<T> future, Boolean timeout) {
        T answer;
        String computeTime;

        try {
            StopWatch watch = new StopWatch();
            watch.start();
            answer = timeout ? future.get(30, TimeUnit.SECONDS) : future.get();
            watch.stop();
            computeTime = formatComputeTime(watch.getTotalTimeMillis());
        } catch (TimeoutException ex) {
            future.cancel(true);
            throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Solution method timed out.");
        } catch (InterruptedException | ExecutionException ex) {
            if (ex.getCause() instanceof ApiError) { throw new ApiError(((ApiError) ex.getCause()).getStatus(), ex.getCause().getMessage()); }
            else { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()); }
        }

        return TimedSolution.builder()
                .answer(answer)
                .computeTime(computeTime)
                .build();
    }

    private static String formatComputeTime(Long computeTime) {
        if (computeTime == 0) { return "< 1 ms"; }
        else if (computeTime < 1000) { return computeTime + " ms"; }
        else { return computeTime / 1000.0 + " s"; }
    }
}