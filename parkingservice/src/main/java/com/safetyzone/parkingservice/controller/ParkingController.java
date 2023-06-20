package com.safetyzone.parkingservice.controller;

import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.exception.CarAlreadyParkedException;
import com.safetyzone.parkingservice.exception.CarNotPresentInParkingException;
import com.safetyzone.parkingservice.exception.ParkingException;
import com.safetyzone.parkingservice.exception.SlotUnavailableException;
import com.safetyzone.parkingservice.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class for the parking service
 * @author Aurobindo.Parida
 * @since 06/20/2023
 *
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {

    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    /**
     * Endpoint to get parking slot information, also with history of the slot
     * @param slot slot request
     * @return SlotInfoResponseDto
     */
    @GetMapping("/slot")
    public SlotInfoResponseDto getSlotInfo(SlotInfoRequestDto slot) {

        try {
            return parkingService.getSlotInfo(slot);
        } catch (ParkingException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), exception);
        }
    }

    /**
     * Endpoint to book parking slot
     * @param car car park request object contains car registration number
     * @return SlotBookResponseDto
     */
    @PostMapping("/park")
    public SlotBookResponseDto parkCar(@RequestBody SlotBookRequestDto car) {

        try {
            return parkingService.parkCar(car);
        } catch(CarAlreadyParkedException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
        } catch(SlotUnavailableException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), exception);
        }
    }

    /**
     * Endpoint to unpark from parking slot
     * @param car
     * @return SlotBookResponseDto
     */
    @PutMapping("/unpark")
    public SlotBookResponseDto unpark(@RequestBody SlotBookRequestDto car) {

        try {
            return parkingService.unParkCar(car);
        } catch(CarNotPresentInParkingException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), exception);
        } catch(ParkingException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }
}
