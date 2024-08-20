package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Aluno> result = alunoRepo.findById(id);
        if( result.isEmpty() ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }
        return result.get();
    }
    
    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno put( 
        @PathVariable long id,    
        @RequestBody Aluno newData
    ){
        Optional<Aluno> result = alunoRepo.findById(id);
        if( result.isEmpty() ) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }   
        if( newData.getNome().isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Nome do aluno é inválido."
            );
        }
        result.get().setNome(newData.getNome());
        return alunoRepo.save(result.get());     
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if( !alunoRepo.existsById(id) ){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );    
        }
        alunoRepo.deleteById(id);
    }
}
