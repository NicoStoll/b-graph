package de.stoll.nicolas.bgraph.person.adapter.in.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Log
@RestControllerAdvice
public class ConstraintViolationControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {

        Map<String, String> errors = e.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error", "VALIDATION_ERROR",
                        "details", errors
                ));
    }
}
