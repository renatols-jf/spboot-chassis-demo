package com.example.demo.entrypoint.rest;

import io.github.renatolsjf.chassis.rendering.Media;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {

    //It doesn't really matter what's the issue. Let's just return that it was a server error and the exception message
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAny(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Media.ofRenderable(false,
                        (m) -> m.print("message", e.getMessage())).render());

    }

}
