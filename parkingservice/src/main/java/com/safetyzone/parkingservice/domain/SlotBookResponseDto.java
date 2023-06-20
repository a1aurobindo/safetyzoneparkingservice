package com.safetyzone.parkingservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
public class SlotBookResponseDto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public static final Long serialVersionId = 1L;
    String carRegNum;
    String color;
    Integer slotId;
    Boolean active;
    Date parkedDate;
    Date unParkedDate;
}
