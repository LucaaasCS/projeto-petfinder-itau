package com.projeto.petfinder.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DataStackTest {
    @Test
    void DataStackCreayion() {
        DataStack dataStack = new DataStack();
        dataStack.setCountry("Brazil");
        dataStack.setRegion("São Paulo");
        dataStack.setAdministrative_area("São Paulo");
        dataStack.setNeighbourhood("Vila Madalena");
        dataStack.setStreet("Rua Heitor Penteado");

        assertEquals("Brazil", dataStack.getCountry());
        assertEquals("São Paulo", dataStack.getRegion());
        assertEquals("São Paulo", dataStack.getAdministrative_area());
        assertEquals("Vila Madalena", dataStack.getNeighbourhood());
        assertEquals("Rua Heitor Penteado", dataStack.getStreet());
    }
}
