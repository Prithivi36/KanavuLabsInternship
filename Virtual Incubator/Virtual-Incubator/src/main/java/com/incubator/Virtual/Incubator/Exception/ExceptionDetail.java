package com.incubator.Virtual.Incubator.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDetail extends RuntimeException{
    private HttpStatus status;
    private String message;
}
