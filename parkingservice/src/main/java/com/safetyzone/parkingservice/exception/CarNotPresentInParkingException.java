package com.safetyzone.parkingservice.exception;

public class CarNotPresentInParkingException extends RuntimeException{

    public CarNotPresentInParkingException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
