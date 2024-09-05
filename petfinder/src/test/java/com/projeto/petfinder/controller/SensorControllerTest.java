package com.projeto.petfinder.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.petfinder.model.PositionResponse;
import com.projeto.petfinder.model.SensorData;
import com.projeto.petfinder.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @Autowired
    private ObjectMapper objectMapper;

    private SensorData sensorData;
    private PositionResponse positionResponse;

    @BeforeEach
    void setUp() {
        sensorData = new SensorData();
        sensorData.setSensorId("sensor123");
        sensorData.setLatitude("-23.550520");
        sensorData.setLongitude("-46.633308");
        sensorData.setDataHora("2024-08-30T12:00:00");

        PositionResponse.Data data = new PositionResponse.Data();
        data.setCountry("Brazil");
        data.setRegion("São Paulo");
        data.setCounty("Central Zone");
        data.setLocality("São Paulo");
        data.setStreet("Av. Paulista");

        positionResponse = new PositionResponse();
        positionResponse.setData(List.of(data));
    }

    @Test
    void testGetLocationFromSensor_Success() throws Exception {
        when(sensorService.getGeolocation(anyString(), anyString())).thenReturn(positionResponse);

        mockMvc.perform(post("/api/sensor/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorData))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0].country").value("Brazil"))
                .andExpect(jsonPath("$.data[0].region").value("São Paulo"))
                .andExpect(jsonPath("$.data[0].street").value("Av. Paulista"));

        verify(sensorService, times(1)).getGeolocation("-23.550520", "-46.633308");
    }

    @Test
    void testGetLocationFromSensor_ServiceReturnsNull() throws Exception {
        when(sensorService.getGeolocation(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/api/sensor/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sensorData))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""));

        verify(sensorService, times(1)).getGeolocation("-23.550520", "-46.633308");
    }

    @Test
    void testGetLocationFromSensor_InvalidInput() throws Exception {
        SensorData invalidSensorData = new SensorData();
        invalidSensorData.setSensorId("sensor123");
        // Missing latitude and longitude

        mockMvc.perform(post("/api/sensor/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidSensorData))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(sensorService, times(0)).getGeolocation(anyString(), anyString());
    }
}
