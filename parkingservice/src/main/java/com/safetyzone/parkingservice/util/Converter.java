package com.safetyzone.parkingservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.entity.Slot;
import com.safetyzone.parkingservice.entity.SlotBookRecord;

public class Converter {

    private Converter(){}
    public static final ObjectMapper mapper = new ObjectMapper();
    public static SlotInfoResponseDto convertToDto(Slot slot) {
        return mapper.convertValue(slot, new TypeReference<>() {
        });
    }

    public static SlotBookResponseDto convertToDto(SlotBookRecord car) {

        return mapper.convertValue(car, new TypeReference<>() {
        });
    }
}
