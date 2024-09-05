package com.projeto.petfinder.model;

import lombok.*;

import java.util.List;

@Data
public class PositionResponse {
    private List<Data> data;

    @lombok.Data
    public static class Data {
        private String country;

        private String region;

        private String county;

        private String locality;

        private String street;

    }
}
