package com.example.DistriSchool.controller;

import com.example.DistriSchool.domain.Aluno;
import com.example.DistriSchool.dto.FiltroAlunoDTO;
import com.example.DistriSchool.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distrischool/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    //Busca múltipla
    @GetMapping
    public List<Aluno> searchAlunos(FiltroAlunoDTO filtro){
        return alunoService.getByFilter(filtro);
    }
    //Busca única - matrícula
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Aluno> searchAlunobyMatricula(@PathVariable String matricula){
        FiltroAlunoDTO filtro = new FiltroAlunoDTO();
        filtro.setMatricula(matricula);

        List<Aluno> resultado = alunoService.getByFilter(filtro);

        if(resultado.size() == 1){
            return ResponseEntity.ok(resultado.get(0));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> searchAlunobyId(@PathVariable Long id){
        return alunoService.getAlunoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoInfo){
        return alunoService.update(id, alunoInfo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Long id){
        if(alunoService.delete(id)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
