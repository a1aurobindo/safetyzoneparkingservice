package com.safetyzone.parkingservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Builder
@Setter
@Getter
public class SlotInfoRequestDto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public static final Long serialVersionId = 1L;
    int slotId;
    boolean history;
}
