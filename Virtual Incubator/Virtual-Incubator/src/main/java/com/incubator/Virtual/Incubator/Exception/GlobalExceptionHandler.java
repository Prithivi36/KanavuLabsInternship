package com.incubator.Virtual.Incubator.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceptionDetail.class)
    public ResponseEntity<ErrorInfo> giveError(ExceptionDetail exceptionDetail, WebRequest webRequest) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(exceptionDetail.getMessage());
        errorInfo.setDateTime(LocalDateTime.now());
        errorInfo.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
