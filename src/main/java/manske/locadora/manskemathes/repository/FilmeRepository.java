package manske.locadora.manskemathes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manske.locadora.manskemathes.model.Classe;
import manske.locadora.manskemathes.model.Filmes;

@Repository
public interface FilmeRepository extends JpaRepository<Filmes, Long> {

   boolean existsByClasse(Classe classe);
    
}
