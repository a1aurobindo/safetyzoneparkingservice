package com.safetyzone.parkingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SlotBookRecord {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int slotCarId;

    @Column
    private String carRegNum;

    @Column
    private String color;

    @Column
    private Integer slotId;

    @Column
    private boolean active;

    @CreatedDate
    @Column
    @Temporal(TemporalType.DATE)
    private Date parkedDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date unParkedDate;

    @JsonIgnore
    @JoinColumn(name = "slotId", insertable = false, updatable = false)
    @ManyToOne
    private Slot slot;
}
