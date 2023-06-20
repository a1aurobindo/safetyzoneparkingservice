package com.safetyzone.parkingservice.service;

import com.safetyzone.parkingservice.component.ParkingComponent;
import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
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
     * @param car
     * @return SlotBookResponseDto
     */
    public SlotBookResponseDto parkCar(SlotBookRequestDto car) {
        return component.parkCar(car);
    }

    /**
     * Method get parking slot information, also with history of the slot
     * @param slot
     * @return SlotInfoResponseDto
     */
    public SlotInfoResponseDto getSlotInfo(SlotInfoRequestDto slot) {
        return component.getSlotInfo(slot);
    }

    /**
     * Endpoint to unpark from parking car
     * @param car
     * @return SlotBookResponseDto
     */
    public SlotBookResponseDto unParkCar(SlotBookRequestDto car) {
        return component.unParkCar(car);
    }
}
