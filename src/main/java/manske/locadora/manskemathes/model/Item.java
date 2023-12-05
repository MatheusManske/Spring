package manske.locadora.manskemathes.model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Data
@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private long id;

    @Column(length = 20)
    private String numSerie;

    @Column(length = 100)
    private String dtAquisicao;

    @Column(length = 100)
    private String tipoItem;

    @ManyToOne
    @JoinColumn(name = "idFilme")
    private Filmes filme;

}
