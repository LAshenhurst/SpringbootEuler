package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Solutions;
import com.spring.euler.domain.TimedSolution;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public abstract class SolutionsHelper {
    private static final List<Solutions> solutions = List.of(Solutions.values());

    public static TimedSolution getSolution(Integer index) {
        if (index - 1 >= solutions.size()) { throw new ApiError(HttpStatus.NOT_FOUND, "Solution not found."); }
        return TimerHelper.run(createCallable(index - 1));
    }

    public static List<TimedSolution> getSolutions(List<Integer> indices) {
        return indices.stream()
                .map(x -> createCallable(x - 1))
                .map(TimerHelper::run)
                .collect(Collectors.toList());
    }

    private static Callable<Object> createCallable(Integer x) {
        return () -> solutions.get(x).run();
    }
}
