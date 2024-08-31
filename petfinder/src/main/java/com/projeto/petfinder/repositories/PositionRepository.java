package com.projeto.petfinder.repositories;

import com.projeto.petfinder.model.pet.Pet;
import com.projeto.petfinder.model.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PositionRepository  extends JpaRepository<Position, UUID> {

}
