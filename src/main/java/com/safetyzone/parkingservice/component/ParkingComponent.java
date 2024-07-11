package com.safetyzone.parkingservice.component;

import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.entity.Slot;
import com.safetyzone.parkingservice.entity.SlotBookRecord;
import com.safetyzone.parkingservice.exception.CarAlreadyParkedException;
import com.safetyzone.parkingservice.exception.CarNotPresentInParkingException;
import com.safetyzone.parkingservice.exception.ParkingException;
import com.safetyzone.parkingservice.exception.SlotUnavailableException;
import com.safetyzone.parkingservice.repository.SlotCarRepository;
import com.safetyzone.parkingservice.repository.SlotRepository;
import com.safetyzone.parkingservice.util.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.safetyzone.parkingservice.exception.ExceptionType.*;

/**
 * Component class for parking service
 * @since 06/20/2023
 * @author Aurobindo.Parida
 *
 */
@Component
public class ParkingComponent {

    SlotCarRepository slotCarRepository;

    SlotRepository slotRepository;

    public ParkingComponent(SlotRepository slotRepository, SlotCarRepository slotCarRepository) {
        this.slotRepository = slotRepository;
        this.slotCarRepository = slotCarRepository;
    }

    /**
     * Method to book parking slot
     * @param car
     * @return SlotBookResponseDto
     */
    @Transactional
    public SlotBookResponseDto parkCar(SlotBookRequestDto car) {

        slotCarRepository.findByCarRegNumAndActive(car.getCarRegNum(), true).stream()
            .findFirst().ifPresent(carDto -> {
                throw new CarAlreadyParkedException(ALREADY_PARKED);
            });
        Slot avSlot = slotRepository.findAll().stream()
            .filter(slot -> CollectionUtils.isEmpty(slot.getCarsList().stream()
                .filter(book -> book.isActive()).collect(Collectors.toList())))
            .findFirst()
            .orElseThrow(() -> new SlotUnavailableException(NO_AVAILABLE_SLOTS));

        SlotBookRecord slotBookRec = SlotBookRecord.builder()
            .carRegNum(car.getCarRegNum())
            .slotId(avSlot.getSlotId())
            .active(true)
            .parkedDate(new Date()).build();

        return Converter.convertToDto(slotCarRepository.saveAndFlush(slotBookRec));
    }

    /**
     * Method get parking slot information, also with history of the slot
     * @param slotDTO
     * @return SlotInfoResponseDto
     */
    public SlotInfoResponseDto getSlotInfo(SlotInfoRequestDto slotDTO) {

        boolean history = slotDTO.isHistory();
        SlotInfoResponseDto slot = Stream.of(slotRepository.findById(slotDTO.getSlotId())
            .orElseThrow(() -> new ParkingException(INVALID_SLOT)))
            .map(Converter::convertToDto).findFirst().get();
        slot.setCarsList(slot.getCarsList().stream()
            .filter(carDto -> (!history ? carDto.getActive() : history))
            .sorted(Comparator.comparing(SlotBookResponseDto::getActive, Comparator.reverseOrder()))
            .collect(Collectors.toList()));

        return slot;
    }

    /**
     * Method to unpark from parking slot
     * @param car
     * @return SlotBookResponseDto
     */
    @Transactional
    public SlotBookResponseDto unParkCar(SlotBookRequestDto car) {

        SlotBookRecord slotBookRec = slotCarRepository.findByCarRegNumAndActive(car.getCarRegNum(), true).stream()
            .findFirst()
            .orElseThrow(() -> new CarNotPresentInParkingException(CAR_NOT_PARKED));
        Optional.ofNullable(slotBookRec.getSlot()).stream()
            .findFirst()
            .orElseThrow(() -> new ParkingException(SYSTEM_ERROR)).getSlotId();
        slotBookRec.setActive(false);
        slotBookRec.setUnParkedDate(new Date());
        slotCarRepository.save(slotBookRec);

        return Converter.convertToDto(slotBookRec);
    }
}
