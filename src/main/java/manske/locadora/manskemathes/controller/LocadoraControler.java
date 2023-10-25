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

import manske.locadora.manskemathes.model.Filmes;
import manske.locadora.manskemathes.repository.FilmeRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")

public class LocadoraControler {

    private final FilmeRepository filmeRepository;

    public LocadoraControler(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Filmes> list() {
        return filmeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Filmes> encontrarFilme (@PathVariable Long id){
        Optional <Filmes> filmeEncontrado = filmeRepository.findById(id);
        return filmeEncontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Filmes create(@RequestBody Filmes movie) {
        return filmeRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        filmeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Filmes movie) {
        Optional<Filmes> filmeEncontrado = filmeRepository.findById(id);

        if (filmeEncontrado.isPresent()){
            Filmes filme = filmeEncontrado.get();
            filme.setNome(movie.getNome());
            filme.setClasse(movie.getClasse());
            filme.setAno(movie.getAno());
            filme.setSinopse(movie.getSinopse());
            filme.setCategoria(movie.getCategoria());

            filmeRepository.save(filme);
        }
    }
}
