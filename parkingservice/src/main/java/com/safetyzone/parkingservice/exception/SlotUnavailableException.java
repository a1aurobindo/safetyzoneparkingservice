package com.safetyzone.parkingservice.exception;

public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
