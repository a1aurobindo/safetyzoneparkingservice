package com.safetyzone.parkingservice.controller;

import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.service.ParkingService;
import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingControllerTest {

    @InjectMocks
    ParkingController parkingController;

    @Mock
    ParkingService parkingService;

    @Mock
    Bucket bucket;

    @Test
    void testPark() {

        SlotBookRequestDto car = SlotBookRequestDto.builder().carRegNum("123456").build();
        SlotBookResponseDto carResp = new SlotBookResponseDto();
        carResp.setCarRegNum(car.getCarRegNum());
        carResp.setSlotId(1);
        when(parkingService.parkCar(car)).thenReturn(carResp);
        when(bucket.tryConsume(1)).thenReturn(true);

        SlotBookResponseDto responseEntity = parkingController.parkCar(car);
        assertThat(responseEntity.getSlotId().equals(carResp.getSlotId())).isTrue();
    }

    @Test
    void testUnPark() {

        SlotBookRequestDto car = SlotBookRequestDto.builder().carRegNum("123456").build();
        SlotBookResponseDto carResp = new SlotBookResponseDto();
        carResp.setCarRegNum(car.getCarRegNum());
        carResp.setSlotId(1);
        when(parkingService.unParkCar(car)).thenReturn(carResp);

        SlotBookResponseDto responseEntity = parkingController.unpark(car);
        assertThat(responseEntity.getSlotId().equals(carResp.getSlotId())).isTrue();
    }

    @Test
    void testSlotInfo() {

        SlotInfoRequestDto slot = SlotInfoRequestDto.builder().slotId(1).build();
        SlotInfoResponseDto slotResp = SlotInfoResponseDto.builder().slotId(1).build();
        when(parkingService.getSlotInfo(slot)).thenReturn(slotResp);

        SlotInfoResponseDto responseEntity = parkingController.getSlotInfo(slot);
        assertThat(responseEntity.getSlotId().equals(slot.getSlotId())).isTrue();
    }
}
