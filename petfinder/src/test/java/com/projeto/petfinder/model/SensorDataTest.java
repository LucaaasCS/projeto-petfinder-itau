package com.projeto.petfinder.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SensorDataTest {
    @Test
    void SensorDataCreation() {
        SensorData data = new SensorData();
        data.setSensorId("sensor1");
        data.setLatitude("47.735549");
        data.setLongitude("-94.548447");
        data.setDataHora("2024-08-30T12:00:00");

        assertEquals("sensor1", data.getSensorId());
        assertEquals("47.735549", data.getLatitude());
        assertEquals("-94.548447", data.getLongitude());
        assertEquals("2024-08-30T12:00:00", data.getDataHora());
        // ... assert other fields
    }
}
