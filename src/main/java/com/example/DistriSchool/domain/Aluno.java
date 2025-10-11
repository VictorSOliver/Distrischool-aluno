package com.example.DistriSchool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rua", column = @Column(name = "endereco_rua")),
            @AttributeOverride(name = "numero", column = @Column(name = "endereco_numero")),
            @AttributeOverride(name = "cep", column = @Column(name = "endereco_cep")), // <-- Mapeando CEP
            @AttributeOverride(name = "cidade", column = @Column(name = "endereco_cidade")),
            @AttributeOverride(name = "estado", column = @Column(name = "endereco_estado"))
    })
    private Endereco endereco;

    private String nome;
    private String matricula;
    private String turma;
    private String contato;

}
