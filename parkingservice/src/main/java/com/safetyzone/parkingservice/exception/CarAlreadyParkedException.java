package com.safetyzone.parkingservice.exception;

public class CarAlreadyParkedException extends RuntimeException {

    public CarAlreadyParkedException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
