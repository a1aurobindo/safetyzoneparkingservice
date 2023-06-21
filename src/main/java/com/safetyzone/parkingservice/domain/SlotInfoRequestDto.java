package com.safetyzone.parkingservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Request DTO class to get parking slot information also with optional history of the slot
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
@Builder
@Setter
@Getter
public class SlotInfoRequestDto implements Serializable {

    public static final Long serialVersionUID = 1L;
    /**{@link Integer}*/
    int slotId;
    boolean history;
}
