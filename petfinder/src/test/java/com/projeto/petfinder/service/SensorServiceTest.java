package com.projeto.petfinder.service;


import com.projeto.petfinder.model.PositionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SensorServiceTest {
    @InjectMocks
    private SensorService sensorService;

    @Mock
    private RestTemplate restTemplate;

    private static final String POSITIONSTACK_API_URL = "http://api.positionstack.com/v1/forward?access_key=5ba395086fa2ab8cc4257d71ef969bbe&query=%s,%s";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGeolocation_Success() {
        String latitude = "-23.550520";
        String longitude = "-46.633308";

        String expectedUrl = String.format(POSITIONSTACK_API_URL, latitude, longitude);

        PositionResponse mockResponse = new PositionResponse();
        PositionResponse.Data data = new PositionResponse.Data();
        data.setCountry("Brazil");
        data.setRegion("São Paulo");
        data.setCounty("Central Zone");
        data.setLocality("São Paulo");
        data.setStreet("Av. Paulista");
        mockResponse.setData(List.of(data));

        when(restTemplate.getForObject(expectedUrl, PositionResponse.class)).thenReturn(mockResponse);

        PositionResponse response = sensorService.getGeolocation(latitude, longitude);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals("Brazil", response.getData().get(0).getCountry());

        verify(restTemplate, times(1)).getForObject(expectedUrl, PositionResponse.class);
    }

    @Test
    void testGetGeolocation_Failure() {
        String latitude = "invalid_latitude";
        String longitude = "invalid_longitude";

        String expectedUrl = String.format(POSITIONSTACK_API_URL, latitude, longitude);

        when(restTemplate.getForObject(expectedUrl, PositionResponse.class)).thenReturn(null);

        PositionResponse response = sensorService.getGeolocation(latitude, longitude);

        assertNull(response);

        verify(restTemplate, times(1)).getForObject(expectedUrl, PositionResponse.class);
    }

}
