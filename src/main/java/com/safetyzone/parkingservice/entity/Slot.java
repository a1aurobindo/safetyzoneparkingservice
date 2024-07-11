package com.safetyzone.parkingservice.entity;


import lombok.*;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class containing slot record
 * @author Aurobindo.Parida
 * @since 06/20/2023
 */
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
