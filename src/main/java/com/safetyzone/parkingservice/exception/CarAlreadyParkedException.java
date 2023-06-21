package com.safetyzone.parkingservice.exception;

/**
 * Exception class when car is already present in the slot
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
public class CarAlreadyParkedException extends RuntimeException {

    public CarAlreadyParkedException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
