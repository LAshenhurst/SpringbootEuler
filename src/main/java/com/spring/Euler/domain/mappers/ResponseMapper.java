package com.spring.Euler.domain.mappers;

import com.spring.Euler.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class ResponseMapper {
    private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private String generateEuler(String val) {
        return val != null ? "https://projecteuler.net/problem=" + val : null;
    }

    public Response generate(String task, Object answer, String notes, Boolean isEuler) {
        String finalNotes = isEuler ? generateEuler(notes) : notes;
        return Response.builder()
                .task(task)
                .answer(answer)
                .notes(finalNotes)
                .timestamp(format.format(new Date()))
                .build();
    }

}
