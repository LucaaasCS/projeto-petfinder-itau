package com.projeto.petfinder.controller;


import com.projeto.petfinder.model.pet.Pet;
import com.projeto.petfinder.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class petController {

    @Autowired
    private PetRepository petRepository;

    // Método para criar um novo Pet
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        pet.setIdSensor(UUID.randomUUID()); // Define um UUID único para cada Pet
        return petRepository.save(pet);
    }

    // Método para buscar todos os Pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Método para buscar um Pet por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para atualizar um Pet
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable UUID id, @RequestBody Pet petDetails) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setLatitude(petDetails.getLatitude());
            pet.setLongitude(petDetails.getLongitude());
            pet.setDataHora(petDetails.getDataHora());
            return ResponseEntity.ok(petRepository.save(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um Pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable UUID id) {
        Optional<Pet> pet = petRepository.findById(id);

        if (pet.isPresent()) {
            petRepository.delete(pet.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

