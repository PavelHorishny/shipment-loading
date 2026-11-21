package com.example.shipmentloading.fleet.web;

import com.example.shipmentloading.fleet.web.exceptions.VehicleAlreadyExistsException;
import com.example.shipmentloading.fleet.web.exceptions.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInput(IllegalArgumentException exception) {
        return new ErrorResponse("INVALID_INPUT", exception.getMessage());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(VehicleNotFoundException exception) {
        return new ErrorResponse("VEHICLE_NOT_FOUND", exception.getMessage());
    }

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAlreadyExists(VehicleAlreadyExistsException exception) {
        return new ErrorResponse("VEHICLE_ALREADY_EXISTS", exception.getMessage());
    }
}
