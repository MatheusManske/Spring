package manske.locadora.manskemathes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manske.locadora.manskemathes.model.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    
}
