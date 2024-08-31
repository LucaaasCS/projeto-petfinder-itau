package com.projeto.petfinder.service;

import com.projeto.petfinder.model.position.Position;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PositionService {

    private final RestTemplate restTemplate;

    public PositionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Position getPositionFromApi(String url) {
        return restTemplate.getForObject(url, Position.class);
    }
}

