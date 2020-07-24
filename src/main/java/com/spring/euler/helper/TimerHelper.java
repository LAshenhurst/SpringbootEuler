package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.TimedSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

@Slf4j
public abstract class TimerHelper {
    public static TimedSolution run(Callable<Object> method) {
        Object answer;
        String  computeTime;

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Object> future = executor.submit(method);

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
