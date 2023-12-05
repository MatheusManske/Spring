package manske.locadora.manskemathes.controller;

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

import manske.locadora.manskemathes.model.Classe;
import manske.locadora.manskemathes.model.Item;
import manske.locadora.manskemathes.repository.FilmeRepository;
import manske.locadora.manskemathes.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/item")

public class ItemControler {

    private final ItemRepository itemRepository;

    private final FilmeRepository filmeRepository;

    public ItemControler(FilmeRepository filmeRepository, ItemRepository itemRepository){
        this.itemRepository = itemRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Item> list(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> encontrarItem(@PathVariable Long id){
        Optional<Item> itemEncontrado = itemRepository.findById(id);
        return itemEncontrado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item create(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       Optional<Item> itemEncontrado = itemRepository.findById(id);
       itemRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Item item){
        Optional<Item> itemEncontrado = itemRepository.findById(id);

        if(itemEncontrado.isPresent()){
            Item itemExistente = itemEncontrado.get();
            itemExistente.setNumSerie(item.getNumSerie());
            itemExistente.setDtAquisicao(item.getDtAquisicao());
            itemExistente.setFilme(item.getFilme());
            itemExistente.setTipoItem(item.getTipoItem());

            itemRepository.save(itemExistente);
        }
    }
}
