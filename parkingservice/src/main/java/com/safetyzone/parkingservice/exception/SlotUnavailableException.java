package com.safetyzone.parkingservice.exception;

/**
 * Exception class when slot is not available
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException(ExceptionType exceptionType) {
        super(exceptionType.msg);
    }
}
