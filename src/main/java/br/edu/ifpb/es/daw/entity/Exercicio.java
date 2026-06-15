package br.edu.ifpb.es.daw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do exercício é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O grupo muscular é obrigatório.")
    @Column(nullable = false)
    private String grupoMuscular;

    @JsonIgnore
    @OneToMany(mappedBy = "exercicio")
    private List<ItemFicha> itens;

    public Exercicio() {
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

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public List<ItemFicha> getItens() {
        return itens;
    }

    public void setItens(List<ItemFicha> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercicio exercicio)) return false;
        return Objects.equals(id, exercicio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}