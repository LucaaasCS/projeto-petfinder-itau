package com.projeto.petfinder.service;

import com.projeto.petfinder.model.PositionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SensorService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String POSITIONSTACK_API_URL = "http://api.positionstack.com/v1/reverse?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query=%s,%s";

    public PositionResponse getGeolocation(String latitude, String longitude) {
        String url = String.format(POSITIONSTACK_API_URL, latitude, longitude);
        PositionResponse pos = restTemplate.getForObject(url, PositionResponse.class);
        return pos;
    }
}
