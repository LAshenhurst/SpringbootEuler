package com.spring.euler.domain.mappers;

import com.spring.euler.domain.TimedSolution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TimedSolutionMapper {
    public static TimedSolution toTimedSolution(Object answer, String computeTime) {
        return TimedSolution.builder()
                .answer(answer)
                .computeTime(computeTime)
                .build();
    }
}
