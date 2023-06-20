package com.safetyzone.parkingservice.exception;

public class ParkingException extends RuntimeException {


    public ParkingException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
