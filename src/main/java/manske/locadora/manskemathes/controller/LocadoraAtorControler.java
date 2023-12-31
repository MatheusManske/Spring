package manske.locadora.manskemathes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import manske.locadora.manskemathes.model.Ator;
import manske.locadora.manskemathes.model.Classe;
import manske.locadora.manskemathes.repository.AtorRepository;
import manske.locadora.manskemathes.repository.ClasseRepository;
import manske.locadora.manskemathes.repository.FilmeRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ator")

public class LocadoraAtorControler {
    
    private final FilmeRepository filmeRepository;

    private final AtorRepository atorRepository;
    
    public LocadoraAtorControler(AtorRepository atorRepository, FilmeRepository filmeRepository) {
        this.atorRepository = atorRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Ator> listar() {
        return atorRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ator criar(@RequestBody Ator ator) {
        return atorRepository.save(ator);
    }

   private boolean validaExcluir(Ator ator) {
        return filmeRepository.existsByAtor(ator);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ator> encontrarAtor(@PathVariable Long id) {
        Optional<Ator> atorEncontrado = atorRepository.findById(id);
        return atorEncontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        Optional<Ator> atorEncontrado = atorRepository.findById(id);

        if(atorEncontrado.isPresent()){
            if (validaExcluir(atorEncontrado.get())){
                throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Ator tem relação com outra"
                );
            } else {
                atorRepository.deleteById(id);
            }
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Ator Ator) {
        Optional<Ator> atorEncontrado = atorRepository.findById(id);

        if (atorEncontrado.isPresent()) {
            Ator atorExistente = atorEncontrado.get();
            atorExistente.setNome(Ator.getNome());
            //atorExistente.setCpf(Ator.getCpf());
           
            atorRepository.save(atorExistente);
        }
    }
}
