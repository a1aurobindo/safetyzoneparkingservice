package com.safetyzone.parkingservice.exception;

/**
 * Exception class when car is not present in the slot
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
public class CarNotPresentInParkingException extends RuntimeException{

    public CarNotPresentInParkingException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
