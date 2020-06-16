package com.spring.Euler.domain.mappers;

import com.spring.Euler.domain.Answer;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public Answer generate(String problem, Object answer, Integer val) {
        return Answer.builder()
                .problem(problem)
                .answer(answer.toString())
                .eulerURL("https://projecteuler.net/problem=" + val)
                .build();
    }

}
