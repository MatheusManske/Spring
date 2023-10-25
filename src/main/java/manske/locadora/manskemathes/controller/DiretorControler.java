package manske.locadora.manskemathes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import manske.locadora.manskemathes.model.Diretor;
import manske.locadora.manskemathes.repository.DiretorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diretor")
public class DiretorControler {

    private final DiretorRepository diretorRepository;

    public DiretorControler(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        diretorRepository.deleteById(id);
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
