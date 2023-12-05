package manske.locadora.manskemathes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import manske.locadora.manskemathes.model.Classe;
import manske.locadora.manskemathes.model.Diretor;
import manske.locadora.manskemathes.repository.DiretorRepository;
import manske.locadora.manskemathes.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diretor")
public class DiretorControler {

    private final DiretorRepository diretorRepository;

    private final FilmeRepository filmeRepository;


    public DiretorControler(DiretorRepository diretorRepository, FilmeRepository filmeRepository) {
        this.diretorRepository = diretorRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Diretor> list() {
        return diretorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> encontrarDiretor(@PathVariable Long id) {
        Optional<Diretor> diretorEncontrado = diretorRepository.findById(id);
        return diretorEncontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Diretor criar(@RequestBody Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    private boolean validaExcluir(Diretor diretor) {
        return filmeRepository.existsByDiretor(diretor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       Optional<Diretor> diretorEncontrado = diretorRepository.findById(id);

        if (diretorEncontrado.isPresent()) {
            if (validaExcluir(diretorEncontrado.get())) {
               throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Diretor tem relação com outra"
            );
            } else {
                diretorRepository.deleteById(id);;
            }
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Diretor diretor) {
        Optional<Diretor> diretorEncontrado = diretorRepository.findById(id);

        if (diretorEncontrado.isPresent()) {
            Diretor diretorExistente = diretorEncontrado.get();
            diretorExistente.setNome(diretor.getNome());

            diretorRepository.save(diretorExistente);
        }
    }
}
