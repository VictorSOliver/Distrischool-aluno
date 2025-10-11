package com.example.DistriSchool.service;

import com.example.DistriSchool.domain.Aluno;
import com.example.DistriSchool.dto.FiltroAlunoDTO;
import com.example.DistriSchool.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> getById(Long id) {
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> getByMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public List<Aluno> getByFilter(FiltroAlunoDTO filtro) {
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            return alunoRepository.findByNomeContainingIgnoreCase(filtro.getNome());
        }

        if (filtro.getTurma() != null && !filtro.getTurma().isEmpty()) {
            return alunoRepository.findByTurmaIgnoreCase(filtro.getTurma());
        }

        return alunoRepository.findAll();
    }

    public Optional<Aluno> update(Long id, Aluno alunoInfo) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome(alunoInfo.getNome());
            aluno.setMatricula(alunoInfo.getMatricula());
            aluno.setDataNascimento(alunoInfo.getDataNascimento());
            aluno.setTurma(alunoInfo.getTurma());
            aluno.setEndereco(alunoInfo.getEndereco());
            return Optional.of(alunoRepository.save(aluno));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        if(!alunoRepository.existsById(id)){
            throw new EmptyResultDataAccessException(
                    String.format("Nenhum Aluno encontrado com o ID %d", id), 1
            );
        }
        alunoRepository.deleteById(id);
    }
}
