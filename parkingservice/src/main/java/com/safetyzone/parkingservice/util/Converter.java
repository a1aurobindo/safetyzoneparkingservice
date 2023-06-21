package com.safetyzone.parkingservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyzone.parkingservice.domain.AuthTokenDto;
import com.safetyzone.parkingservice.domain.SlotBookResponseDto;
import com.safetyzone.parkingservice.domain.SlotInfoResponseDto;
import com.safetyzone.parkingservice.entity.AuthToken;
import com.safetyzone.parkingservice.entity.Slot;
import com.safetyzone.parkingservice.entity.SlotBookRecord;

import java.lang.reflect.Type;

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

    public static AuthToken convertToEntity(AuthTokenDto tokenDto) {

        return mapper.convertValue(tokenDto, new TypeReference<>() {});
    }

    public static AuthTokenDto convertToEntity(AuthToken token) {

        return mapper.convertValue(token, new TypeReference<>() {});
    }
}
