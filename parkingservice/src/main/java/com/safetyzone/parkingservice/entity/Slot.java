package com.safetyzone.parkingservice.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer slotId;

    String location;

    @OneToMany(mappedBy = "slot", fetch = FetchType.LAZY)
    List<SlotBookRecord> carsList;
}
