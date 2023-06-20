package com.safetyzone.parkingservice.exception;

/**
 * Exception class for parking exception
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
public class ParkingException extends RuntimeException {

    public ParkingException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
