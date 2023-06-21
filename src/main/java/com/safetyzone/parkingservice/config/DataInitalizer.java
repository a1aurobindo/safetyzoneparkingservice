package com.safetyzone.parkingservice.config;

import com.safetyzone.parkingservice.entity.Slot;
import com.safetyzone.parkingservice.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates parking slot data in H2 table
 * @author Aurobindo.Parida
 */
@Component
public class DataInitalizer implements ApplicationRunner {

    @Value("${parking.lot-size}")
    Integer dataSize;

    @Autowired
    SlotRepository slotRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Slot> slotList = new ArrayList<>(dataSize);

        for(int i = 0; i < dataSize; i++) {
            slotList.add(Slot.builder().slotId(i).location("LOC-" +i).build());
        }

        slotRepository.saveAll(slotList);
    }
}
