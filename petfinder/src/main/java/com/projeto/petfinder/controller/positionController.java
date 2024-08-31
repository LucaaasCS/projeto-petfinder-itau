package com.projeto.petfinder.controller;


import com.projeto.petfinder.model.position.Position;
import com.projeto.petfinder.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/position")
public class positionController {
    @Autowired
    private PositionService positionService;

    // Método para consumir a API externa e retornar os dados
    @GetMapping
    public Position getPosition(@RequestParam String url) {
        return positionService.getPositionFromApi(url);
    }
}

