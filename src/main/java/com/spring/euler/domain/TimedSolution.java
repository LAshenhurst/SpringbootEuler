package com.spring.euler.domain;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimedSolution {
    @NotNull
    private Object answer;

    @NotNull
    private String computeTime;
}
