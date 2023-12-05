package manske.locadora.manskemathes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Ator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    /*@Column(length = 11, nullable = false)
    private String cpf;*/

    // @ManyToMany(mappedBy = "ator")
    // private List<Filmes> filmes = new ArrayList<>();




    
}
