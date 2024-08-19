package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import application.model.Aluno;
import application.repository.AlunoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/alunos")
public class Alunos {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping
    public Iterable<Aluno> list(){
        return alunoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Aluno details(@PathVariable long id){
        return alunoRepo.findById(id).get();
    }
    
    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    @PutMapping("path/{id}")
    public Aluno put( 
        @PathVariable long id,    
        @RequestBody Aluno newData
    ){
        Optional<Aluno> result = alunoRepo.findById(id);
        result.get().setNome(newData.getNome());
        return alunoRepo.save(result.get());     
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam long id) {
        alunoRepo.deleteById(id);
    }
}
