package com.ps.pservice.controllers;

import com.ps.pservice.dtos.ExceptionDto;
import com.ps.pservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductControllerAdvice {

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
//        System.out.println("No product found with the given id.");
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage("No product found with the given id.");
//        exceptionDto.setStatus(HttpStatus.BAD_GATEWAY);
//
//        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        System.out.println("No product found with the given id.");
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No product found with the given id.");
        exceptionDto.setStatus(HttpStatus.NOT_FOUND);

        return exceptionDto;
    }
}
