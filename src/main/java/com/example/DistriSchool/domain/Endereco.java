package com.example.DistriSchool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
