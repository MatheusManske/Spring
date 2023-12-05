package manske.locadora.manskemathes.model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "filme_ator",
        joinColumns = @JoinColumn(name = "filmeid"),
        inverseJoinColumns = @JoinColumn(name = "idAtor")
    )
    private List <Ator> ator;

   /*@ManyToOne
    @JoinColumn(name = "idAtor")
    private Ator ator;*/

    @Column(length = 120, nullable = false)
    private String categoria;

    @Column(length = 4, nullable = false)
    private String ano;

    @Column(length = 200, nullable = false)
    private String sinopse;
    
}
