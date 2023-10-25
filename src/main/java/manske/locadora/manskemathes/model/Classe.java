package manske.locadora.manskemathes.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Classe implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String valor;

    @Column(length = 10, nullable = false)
    private String prazo;
    
}
