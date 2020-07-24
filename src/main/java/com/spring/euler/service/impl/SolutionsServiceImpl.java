package com.spring.euler.service.impl;

import com.spring.euler.domain.Solutions;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.helper.TimerHelper;
import com.spring.euler.service.SolutionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolutionsServiceImpl implements SolutionsService {
    private final List<Solutions> solutions = List.of(Solutions.values());

    public TimedSolution getSolution(Integer index) {
        return TimerHelper.run(() -> {
            log.info("Calculating solution to problem {}", index);
            return solutions.get(index - 1).run();
        });
    }
}
