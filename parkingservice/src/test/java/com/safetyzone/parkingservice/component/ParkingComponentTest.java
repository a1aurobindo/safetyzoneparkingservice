package com.safetyzone.parkingservice.component;

import com.safetyzone.parkingservice.domain.SlotBookRequestDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoRequestDto;
import com.safetyzone.parkingservice.entity.Slot;
import com.safetyzone.parkingservice.entity.SlotBookRecord;
import com.safetyzone.parkingservice.exception.CarAlreadyParkedException;
import com.safetyzone.parkingservice.exception.ParkingException;
import com.safetyzone.parkingservice.exception.SlotUnavailableException;
import com.safetyzone.parkingservice.repository.SlotCarRepository;
import com.safetyzone.parkingservice.repository.SlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingComponentTest {

    @InjectMocks
    ParkingComponent parkingComponent;

    @Mock
    SlotRepository slotRepository;

    @Mock
    SlotCarRepository slotCarRepository;

    @Captor
    ArgumentCaptor<SlotBookRecord> slotCarArgumentCaptor;

    @Captor
    ArgumentCaptor<String> carRegNumCaptor;

    @Captor
    ArgumentCaptor<Boolean> historyFlagCaptor;

    @Test
    public void test_parkCar_alreadyParked() {

        String carRegNum = "CA12AH3232";
        SlotBookRequestDto slotBookRequestDto = SlotBookRequestDto.builder().carRegNum(carRegNum).color("red").build();
        SlotBookRecord slotCar = SlotBookRecord.builder().slotId(1).color("red").carRegNum(carRegNum).build();

        when(slotCarRepository.findByCarRegNumAndActive(carRegNum, true)).thenReturn(Optional.of(slotCar));

        assertThrows(CarAlreadyParkedException.class, () -> parkingComponent.parkCar(slotBookRequestDto));
    }

    @Test
    public void test_parkCar_noAvailableSpot() {

        String carRegNum = "CA12AH3232";
        String color = "red";
        Integer slotId = 1;
        SlotBookRequestDto slotBookRequestDto = SlotBookRequestDto.builder().carRegNum(carRegNum).color(color).build();
        SlotBookRecord slotCar = SlotBookRecord.builder().slotId(slotId).color(color).active(true).carRegNum(carRegNum).build();
        Slot slot = Slot.builder().slotId(slotId).location("NE").carsList(Arrays.asList(slotCar)).build();

        when(slotCarRepository.findByCarRegNumAndActive(carRegNum, true)).thenReturn(Optional.empty());
        when(slotRepository.findAll()).thenReturn(Arrays.asList(slot));

        assertThrows(SlotUnavailableException.class, () -> parkingComponent.parkCar(slotBookRequestDto));
    }

    @Test
    public void test_parkCar_parkSuccess() {

        String carRegNum = "CA12AH3232";
        String color = "red";
        Integer slotId = 1;
        SlotBookRequestDto slotBookRequestDto = SlotBookRequestDto.builder().carRegNum(carRegNum).color(color).build();
        SlotBookRecord slotCar = SlotBookRecord.builder().slotId(slotId).color(color).carRegNum(carRegNum).build();
        Slot slot = Slot.builder().slotId(slotId).location("NE").carsList(new ArrayList<>()).build();

        when(slotCarRepository.findByCarRegNumAndActive(carRegNum, true)).thenReturn(Optional.empty());
        when(slotRepository.findAll()).thenReturn(Arrays.asList(slot));
        SlotBookResponseDto resp = parkingComponent.parkCar(slotBookRequestDto);

        verify(slotCarRepository).saveAndFlush(slotCarArgumentCaptor.capture());
        assertEquals(slotCar.getSlotId(), slotCarArgumentCaptor.getValue().getSlotId());
    }

    @Test
    public void test_getSlotInfo_invalidSlot() {

        SlotInfoRequestDto slotBookRequestDto = SlotInfoRequestDto.builder().slotId(1).build();
        when(slotRepository.findById(slotBookRequestDto.getSlotId())).thenReturn(Optional.empty());
        assertThrows(ParkingException.class, () -> parkingComponent.getSlotInfo(slotBookRequestDto));
    }

    @Test
    public void test_getSlotInfo_validSlot() {

        String carRegNum = "CA12AH3232";
        String color = "red";
        Integer slotId = 1;
        SlotInfoRequestDto slotBookRequestDto = SlotInfoRequestDto.builder().slotId(slotId).history(false).build();
        SlotBookRecord slotCar = SlotBookRecord.builder().slotId(slotId).color(color).carRegNum(carRegNum).build();
        Slot slot = Slot.builder().slotId(slotId).location("NE").carsList(Arrays.asList(slotCar)).build();

        when(slotRepository.findById(slotBookRequestDto.getSlotId())).thenReturn(Optional.of(slot));

        assertEquals(slotId, parkingComponent.getSlotInfo(slotBookRequestDto).getSlotId());
    }

    @Test
    public void test_unParkCar_Error() {

        String carRegNum = "CA12AH3232";
        String color = "red";
        SlotBookRequestDto slotBookRequestDto = SlotBookRequestDto.builder().carRegNum(carRegNum).color(color).build();
        SlotBookRecord slotCar = SlotBookRecord.builder().color(color).carRegNum(carRegNum).build();

        when(slotCarRepository.findByCarRegNumAndActive(carRegNum, true)).thenReturn(Optional.of(slotCar));

        assertThrows(ParkingException.class, () -> parkingComponent.unParkCar(slotBookRequestDto));
    }

    @Test
    public void test_unParkCar_Success() {

        String carRegNum = "CA12AH3232";
        String color = "red";
        Integer slotId = 1;
        SlotBookRequestDto slotBookRequestDto = SlotBookRequestDto.builder().carRegNum(carRegNum).color(color).build();
        SlotBookRecord slotCar = SlotBookRecord.builder().color(color).slotId(slotId).carRegNum(carRegNum).build();
        Slot slot = Slot.builder().slotId(slotId).location("NE").carsList(Arrays.asList(slotCar)).build();
        slotCar.setSlot(slot);

        when(slotCarRepository.findByCarRegNumAndActive(carRegNum, true)).thenReturn(Optional.of(slotCar));

        assertEquals(slotId, parkingComponent.unParkCar(slotBookRequestDto).getSlotId());
        verify(slotCarRepository).findByCarRegNumAndActive(carRegNumCaptor.capture(), historyFlagCaptor.capture());
        assertEquals(carRegNum, carRegNumCaptor.getValue());
        assertEquals(true, historyFlagCaptor.getValue());
    }
}
