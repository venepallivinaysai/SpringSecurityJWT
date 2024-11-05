package com.SpringSecurity.Security.exception;

import com.SpringSecurity.Security.entity.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class BaseError extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<ErrorResult> userException(CustomizedException customizedException, WebRequest webRequest){
        ErrorResult errorResponse= new ErrorResult(HttpStatus.NOT_FOUND, customizedException.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}
