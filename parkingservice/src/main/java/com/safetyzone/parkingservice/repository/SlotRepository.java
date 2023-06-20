package com.safetyzone.parkingservice.repository;

import com.safetyzone.parkingservice.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for gettng parking slot information
 * @author Aurobindo.Parida
 * @since 06/20/2023
 *
 */
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
