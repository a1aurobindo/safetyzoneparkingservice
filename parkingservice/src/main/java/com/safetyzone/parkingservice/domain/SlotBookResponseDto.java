package com.safetyzone.parkingservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Response DTO class containing slot booking information
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class SlotBookResponseDto implements Serializable {

    public static final Long serialVersionUID = 1L;
    String carRegNum;
    Integer slotId;
    Boolean active;
    Date parkedDate;
    Date unParkedDate;
}
