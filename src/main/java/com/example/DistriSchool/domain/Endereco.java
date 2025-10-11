package com.example.DistriSchool.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
}
