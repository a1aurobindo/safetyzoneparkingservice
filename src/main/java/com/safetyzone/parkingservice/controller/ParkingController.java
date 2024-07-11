package com.safetyzone.parkingservice.controller;

import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.exception.CarAlreadyParkedException;
import com.safetyzone.parkingservice.exception.CarNotPresentInParkingException;
import com.safetyzone.parkingservice.exception.ParkingException;
import com.safetyzone.parkingservice.exception.SlotUnavailableException;
import com.safetyzone.parkingservice.service.EmailService;
import com.safetyzone.parkingservice.service.ParkingService;
import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller class for the parking service
 * @author Aurobindo.Parida
 * @since 06/20/2023
 *
 */
@Tag(name = "Parking")
@RestController
@RequestMapping("/parking")
public class ParkingController {

    private ParkingService parkingService;

    private Bucket bucket;

    private EmailService emailService;

    /**
     * Constructor with defining a rete limit for calling the park endpoint
     * @param parkingService
     * @param bucket rate limiter
     */
    public ParkingController(ParkingService parkingService, Bucket bucket, EmailService emailService) {
        this.parkingService = parkingService;
        this.bucket = bucket;
        this.emailService = emailService;
    }

    /**
     * Endpoint to get parking slot information, also with history of the slot
     * @param slot slot request
     * @return SlotInfoResponseDto
     */
    @Operation(description = "This method is used to get the information about a parkng slot")
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
    @Operation(description = "This method is used to park a car at a slot")
    public SlotBookResponseDto parkCar(@RequestBody SlotBookRequestDto car) {
        if (bucket.tryConsume(1)) {
            try {
                return parkingService.parkCar(car);
            } catch (CarAlreadyParkedException exception) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception);
            } catch (SlotUnavailableException exception) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), exception);
            }
        }
        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }

    /**
     * Endpoint to unpark from parking slot
     * @param car
     * @return SlotBookResponseDto
     */
    @PutMapping("/unpark")
    @Operation(description = "This method is used to unpark a car at a slot")
    public SlotBookResponseDto unpark(@RequestBody SlotBookRequestDto car) {

        try {
            return parkingService.unParkCar(car);
        } catch(CarNotPresentInParkingException exception) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), exception);
        } catch(ParkingException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    @GetMapping("/sendemail")
    public void sendEmail() {
        String to = "a1aurobindo@live.in";
        String subject = "test";
        String body = "testing email feature";
        this.emailService.sendEmail(to, subject, body);
    }
}
