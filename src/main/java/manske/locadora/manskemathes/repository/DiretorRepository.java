package manske.locadora.manskemathes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manske.locadora.manskemathes.model.Diretor;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    
}
