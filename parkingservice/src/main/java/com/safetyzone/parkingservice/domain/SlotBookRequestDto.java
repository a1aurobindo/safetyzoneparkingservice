package com.safetyzone.parkingservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import java.io.Serializable;

/**
 * Request DTO class for booking a parking slot containing a car registration number and optional car color
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class SlotBookRequestDto implements Serializable {

    public static final Long serialVersionUID = 1L;
    String carRegNum;
    String color;
}
