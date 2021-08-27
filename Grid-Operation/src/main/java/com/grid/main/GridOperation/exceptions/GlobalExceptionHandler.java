package com.grid.main.GridOperation.exceptions;

import com.grid.main.GridOperation.domain.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataSourceKeyNotPresent.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleDataSourceKeyNotPresent(DataSourceKeyNotPresent ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .reason(ex.toString())
                .build();
    }
}
