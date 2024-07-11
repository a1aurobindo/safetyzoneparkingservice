package com.safetyzone.parkingservice.repository;

import com.safetyzone.parkingservice.entity.SlotBookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository for the booking parking slot
 * @author Aurobindo.Parida
 * @since 06/20/2023
 *
 */
public interface SlotCarRepository extends JpaRepository<SlotBookRecord, String> {

    Optional<SlotBookRecord> findByCarRegNumAndActive(String carRegNum, boolean active);
}
