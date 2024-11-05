package com.SpringSecurity.Security.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResult {

    private HttpStatus status;
    private String message;
}
