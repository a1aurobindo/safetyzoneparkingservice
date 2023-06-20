package com.safetyzone.parkingservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.AccessLevel;

import java.io.Serializable;
import java.util.List;

/**
 * Response DTO class containing parking slot information also with optional history of the slot
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class SlotInfoResponseDto implements Serializable {

    public static final Long serialVersionUID = 1L;
    Integer slotId;
    String location;
    List<SlotBookResponseDto> carsList;
}
