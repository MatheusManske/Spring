package manske.locadora.manskemathes.model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Filmes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name ="idClasse")
    private Classe classe;

    @Column(length = 120, nullable = false)
    private String categoria;

    @Column(length = 4, nullable = false)
    private String ano;

    @Column(length = 200, nullable = false)
    private String sinopse;
    
}
