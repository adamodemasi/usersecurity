package it.pegasoft.usersecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "libro")
public class Libro implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotBlank
    private String titolo;
    @NotBlank
    private String autore;
    @NotBlank
    private String genere;
    @NotBlank
    private Double prezzo;

    @Override
    public String toString(){
        return "Libro{" +
                "id= " + id +
                ", titolo= '" + titolo + '/' +
                ", autore= '" + autore + '/' +
                ", genere= '" + genere + '/' +
                ", prezzo= '" + prezzo + '/' +
                '}';
    }
}
