package com.spring.euler.domain.mappers;

import com.spring.euler.domain.TimedSolution;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class TimedSolutionMapper {
    public static TimedSolution toTimedSolution(Object answer, String computeTime) {
        return TimedSolution.builder()
                .answer(answer)
                .computeTime(computeTime)
                .build();
    }
}
