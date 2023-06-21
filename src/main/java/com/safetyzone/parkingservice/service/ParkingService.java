package com.safetyzone.parkingservice.service;

import com.safetyzone.parkingservice.component.ParkingComponent;
import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service class for the parking service
 * @author Aurobindo.Parida
 * @since 06/20/2023
 *
 */
@Service
public class ParkingService {

    ParkingComponent component;

    public ParkingService(ParkingComponent component) {
        this.component = component;
    }

    /**
     * Method to book parking slot
     * @param car  {@link SlotBookRequestDto} contains car registration number
     * @return SlotBookResponseDto
     */
    public SlotBookResponseDto parkCar(SlotBookRequestDto car) {
        return component.parkCar(car);
    }

    /**
     * Method get parking slot information, also with history of the slot
     * @param slot {@link SlotInfoRequestDto} contains slotId and optional history value
     * @return SlotInfoResponseDto
     */
    public SlotInfoResponseDto getSlotInfo(SlotInfoRequestDto slot) {
        return component.getSlotInfo(slot);
    }

    /**
     * Endpoint to unpark from parking car
     * @param car {@link SlotBookRequestDto} contains car registration number
     * @return SlotBookResponseDto
     */
    public SlotBookResponseDto unParkCar(SlotBookRequestDto car) {
        return component.unParkCar(car);
    }
}
