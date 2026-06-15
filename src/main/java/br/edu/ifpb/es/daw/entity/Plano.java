package br.edu.ifpb.es.daw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Objects;

@Entity
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do plano é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull(message = "O valor do plano é obrigatório.")
    @Positive(message = "O valor do plano deve ser positivo.")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "A duração do plano é obrigatória.")
    @Positive(message = "A duração do plano deve ser positiva.")
    @Column(nullable = false)
    private Integer duracao;

    @JsonIgnore
    @OneToMany(mappedBy = "plano")
    private List<Matricula> matriculas;

    public Plano() {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plano plano)) return false;
        return Objects.equals(id, plano.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}