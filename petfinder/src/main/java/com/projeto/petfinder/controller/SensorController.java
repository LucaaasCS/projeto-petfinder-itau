package com.projeto.petfinder.controller;

import com.projeto.petfinder.model.PositionResponse;
import com.projeto.petfinder.model.SensorData;
import com.projeto.petfinder.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping("/location")
    public ResponseEntity<PositionResponse> getLocationFromSensor(@RequestBody SensorData sensorData) {
        PositionResponse positionResponse = sensorService.getGeolocation(sensorData.getLatitude(), sensorData.getLongitude());
        return ResponseEntity.ok(positionResponse);
    }
}