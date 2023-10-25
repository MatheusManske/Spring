package manske.locadora.manskemathes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manske.locadora.manskemathes.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
    
}
