package com.safetyzone.parkingservice.repository;

import com.safetyzone.parkingservice.entity.SlotBookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotCarRepository extends JpaRepository<SlotBookRecord, String> {

    Optional<SlotBookRecord> findByCarRegNumAndActive(String carRegNum, boolean active);
}
