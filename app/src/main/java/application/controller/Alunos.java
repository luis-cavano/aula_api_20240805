package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import application.model.Aluno;
import application.repository.AlunoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/alunos")
public class Alunos {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping
    public Iterable<Aluno> list(){
        return alunoRepo.findAll();
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }
}
