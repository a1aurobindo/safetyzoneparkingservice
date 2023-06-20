package com.safetyzone.parkingservice.repository;

import com.safetyzone.parkingservice.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
