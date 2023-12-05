package manske.locadora.manskemathes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manske.locadora.manskemathes.model.Filmes;
import manske.locadora.manskemathes.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByFilme(Filmes filme);
}
