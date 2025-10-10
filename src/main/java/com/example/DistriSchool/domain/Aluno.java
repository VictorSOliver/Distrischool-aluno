package com.example.DistriSchool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Aluno {
    private long id;
    private String nome;
    private String matricula;
    private String turma;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    private Endereco endereco;
    private String contato;
}
