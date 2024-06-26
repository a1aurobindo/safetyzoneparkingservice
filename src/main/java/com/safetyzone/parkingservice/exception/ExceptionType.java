package com.safetyzone.parkingservice.exception;

/**
 * Enum containg different Exception types the system is generating
 */
public enum ExceptionType {

    ALREADY_PARKED("Car is already parked"),
    NO_AVAILABLE_SLOTS("Car is already parked"),
    INVALID_SLOT("Invalid slot"),
    CAR_NOT_PARKED("Car is not parked."),
    SYSTEM_ERROR("System Error: car is in parking but not assigned to a slot");

    final String msg;

    ExceptionType(String s) {
        this.msg = s;
    }
}
