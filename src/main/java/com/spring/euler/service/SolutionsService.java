package com.spring.euler.service;

import com.spring.euler.domain.TimedSolution;

public interface SolutionsService {
    TimedSolution getSolution(Integer index);
}