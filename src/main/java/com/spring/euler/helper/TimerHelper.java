package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiException;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.domain.mappers.TimedSolutionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

public final class TimerHelper {
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public static TimedSolution run(Callable<Object> method) { return processMethod(EXECUTOR.submit(method), true); }

    public static TimedSolution runUntilComplete(Callable<Object> method) { return processMethod(EXECUTOR.submit(method), false); }

    private static TimedSolution processMethod(Future<Object> future, Boolean timeout) {
        Object answer;
        String computeTime;

        try {
            StopWatch watch = new StopWatch();
            watch.start();
            answer = timeout ? future.get(30, TimeUnit.SECONDS) : future.get();
            watch.stop();
            computeTime = formatComputeTime(watch.getTotalTimeMillis());
        } catch (TimeoutException ex) {
            future.cancel(true);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Solution method timed out.");
        } catch (InterruptedException | ExecutionException ex) {
            if (ex.getCause() instanceof ApiException) { throw new ApiException(((ApiException) ex.getCause()).getStatus(), ex.getCause().getMessage()); }
            else { throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()); }
        }

        return TimedSolutionMapper.toTimedSolution(answer, computeTime);
    }

    private static String formatComputeTime(Long computeTime) {
        if (computeTime == 0) { return "< 1 ms"; }
        else if (computeTime < 1000) { return computeTime + " ms"; }
        else { return computeTime / 1000.0 + " s"; }
    }
}