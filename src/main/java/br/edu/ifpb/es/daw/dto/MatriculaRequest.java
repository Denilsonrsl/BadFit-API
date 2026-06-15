package br.edu.ifpb.es.daw.dto;

import java.time.LocalDate;

public class MatriculaRequest {

    private LocalDate dataInicio;
    private Long alunoId;
    private Long planoId;

    public MatriculaRequest() {
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }
}