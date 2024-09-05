package com.projeto.petfinder.service;

import com.projeto.petfinder.model.PositionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SensorService {
    private final RestTemplate restTemplate;
    private static final String POSITIONSTACK_API_URL = "http://api.positionstack.com/v1/forward?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query=%s,%s";

    public SensorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PositionResponse getGeolocation(String latitude, String longitude) {
        String url = String.format(POSITIONSTACK_API_URL, latitude, longitude);
        return restTemplate.getForObject(url, PositionResponse.class);
    }

}

/*
private static final String POSITIONSTACK_API_URL = "https://api.positionstack.com/v1/reverse?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query=40.7638435,-73.9729691";

public SensorService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
}

public  PositionResponse getGeolocation(String latitude, String longitude) {
    String url = "https://api.positionstack.com/v1/reverse?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query=40.7638435,-73.9729691";
    RestTemplate rt =  new RestTemplate();
    PositionResponse result =  rt.getForObject(url, PositionResponse.class);
    //var result2 =  rt.getForEntity(url, PositionResponse.class);
    ResponseEntity<PositionResponse> repr = rt.getForEntity(url, PositionResponse.class);

    System.out.println(repr.getBody());
    return repr.getBody();
}*/
