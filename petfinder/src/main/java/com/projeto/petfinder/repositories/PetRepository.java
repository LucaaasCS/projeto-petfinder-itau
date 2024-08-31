package com.projeto.petfinder.repositories;

import com.projeto.petfinder.model.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository  extends JpaRepository<Pet, UUID> {
}
