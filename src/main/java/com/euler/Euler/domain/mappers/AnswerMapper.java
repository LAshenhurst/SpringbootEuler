package com.euler.Euler.domain.mappers;

import com.euler.Euler.domain.Answer;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public Answer generate(String problem, Object answer) {
        return Answer.builder()
                .problem(problem)
                .answer(answer.toString())
                .build();
    }

}
