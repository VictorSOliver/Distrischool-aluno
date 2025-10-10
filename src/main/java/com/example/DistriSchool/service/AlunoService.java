package com.example.DistriSchool.service;

import com.example.DistriSchool.domain.Aluno;
import com.example.DistriSchool.dto.FiltroAlunoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private static List<Aluno> alunos =  new ArrayList<Aluno>();
    private static Long proximoId = 1L;

    public Aluno save(Aluno aluno) {
        proximoId++;
        aluno.setId(proximoId);
        alunos.add(aluno);
        return aluno;
    }

    public List<Aluno> getAll() {
        return alunos;
    }

    public List<Aluno> getByFilter(FiltroAlunoDTO filtro) {
        return alunos.stream()
                .filter(aluno -> filtro.getNome() == null || aluno.getNome().contains(filtro.getNome()))
                .filter(aluno -> filtro.getTurma() == null || aluno.getTurma().equalsIgnoreCase(filtro.getTurma()))
                .filter(aluno -> filtro.getMatricula() == null || aluno.getMatricula().equalsIgnoreCase(filtro.getMatricula()))
                .collect(Collectors.toList());
    }
//    public List<Aluno> getByName(String name) {
//        return alunos.stream()
//                .filter(a -> a.getNome().contains(name))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<Aluno> getByMatricula(String matricula) {
//        return alunos.stream()
//                .filter(a -> a.getMatricula().equalsIgnoreCase(matricula))
//                .findFirst();
//    }

    public List<Aluno> getByTurma(String turma) {
        return alunos.stream()
                .filter(a -> a.getTurma().equalsIgnoreCase(turma))
                .collect(Collectors.toList());
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return  alunos.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public Optional<Aluno> update(Long id, Aluno alunoInfo){
        Optional<Aluno> alunoOptional = getAlunoById(id);

        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            aluno.setNome(alunoInfo.getNome());
            aluno.setMatricula(alunoInfo.getMatricula());
//            aluno.setEmail(alunoInfo.getEmail()); preciso ver como alterar esta linha!
            return Optional.of(aluno);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
       return alunos.removeIf( aluno -> aluno.getId() == id);
    }
}
