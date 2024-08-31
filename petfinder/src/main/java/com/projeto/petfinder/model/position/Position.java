package com.projeto.petfinder.model.position;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "position")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private String pais;

    private String estado;

    private String cidade;

    private String bairro;

    private String endereco;


}
