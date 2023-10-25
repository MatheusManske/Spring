package manske.locadora.manskemathes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import manske.locadora.manskemathes.model.Classe;
import manske.locadora.manskemathes.repository.ClasseRepository;
import manske.locadora.manskemathes.repository.DiretorRepository;
import manske.locadora.manskemathes.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nova")

public class ClasseControler {
   
    private final ClasseRepository classeRepository;

    
    private final FilmeRepository filmeRepository;

    
    public ClasseControler(ClasseRepository classeRepository, FilmeRepository filmeRepository) {
        this.classeRepository = classeRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Classe> list() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> encontrarClasse(@PathVariable Long id) {
        Optional<Classe> classeEncontrada = classeRepository.findById(id);
        return classeEncontrada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Classe create(@RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    private boolean validaExcluir(Classe classe) {
        return filmeRepository.existsByClasse(classe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Classe> classeEncontrada = classeRepository.findById(id);

        if(classeEncontrada.isPresent()){
            if (validaExcluir(classeEncontrada.get())){
                    System.out.println("ola");
            }
            else{
                classeRepository.deleteById(id);
            }
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Classe classe) {
        Optional<Classe> classeEncontrada = classeRepository.findById(id);

        if (classeEncontrada.isPresent()) {
            Classe classeExistente = classeEncontrada.get();
            classeExistente.setNome(classe.getNome());
            classeExistente.setValor(classe.getValor());
            classeExistente.setPrazo(classe.getPrazo());

            classeRepository.save(classeExistente);
        }
    }
}
