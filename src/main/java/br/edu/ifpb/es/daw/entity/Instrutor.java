package br.edu.ifpb.es.daw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do instrutor é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O CPF do instrutor é obrigatório.")
    @Column(nullable = false)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "instrutor")
    private List<Ficha> fichas;

    public Instrutor() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrutor instrutor)) return false;
        return Objects.equals(id, instrutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}