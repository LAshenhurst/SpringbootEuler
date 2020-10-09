package com.spring.euler.domain.mappers;

import com.spring.euler.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class ResponseMapper {
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static String generateEuler(String val) { return val != null ? "https://projecteuler.net/problem=" + val : null; }

    public static Response toResponse(String task, Object answer, String notes, Boolean isEuler, String computeTime) {
        String finalNotes = isEuler ? generateEuler(notes) : notes;
        return Response.builder()
                .task(task)
                .answer(answer)
                .notes(finalNotes)
                .timestamp(DATETIME_FORMAT.format(new Date()))
                .computeTime(computeTime)
                .build();
    }
}
