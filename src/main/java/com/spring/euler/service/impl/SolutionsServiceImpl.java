package com.spring.euler.service.impl;

import com.spring.euler.domain.Solutions;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.helper.TimerHelper;
import com.spring.euler.service.SolutionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolutionsServiceImpl implements SolutionsService {
    private final List<Solutions> solutions = List.of(Solutions.values());

    public TimedSolution getSolution(Integer index) {
        return TimerHelper.run(createCallable(index - 1));
    }

    public List<TimedSolution> getSolutions(List<Integer> indices) {
        List<Callable<Object>> methods = indices
                .stream()
                .map(x -> createCallable(x - 1))
                .collect(Collectors.toList());

        return TimerHelper.runMany(methods);
    }

    private Callable<Object> createCallable(Integer x) {
        return () -> solutions.get(x).run();
    }
}
