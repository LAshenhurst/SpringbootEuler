package com.spring.euler.service;

import com.spring.euler.domain.TimedSolution;

import java.util.List;

public interface SolutionsService {
    TimedSolution getSolution(Integer index);
    List<TimedSolution> getSolutions(List<Integer> indices);
}