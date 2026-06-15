package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.dto.MatriculaRequest;
import br.edu.ifpb.es.daw.entity.Aluno;
import br.edu.ifpb.es.daw.entity.Matricula;
import br.edu.ifpb.es.daw.entity.Pagamento;
import br.edu.ifpb.es.daw.entity.Plano;
import br.edu.ifpb.es.daw.repository.AlunoRepository;
import br.edu.ifpb.es.daw.repository.MatriculaRepository;
import br.edu.ifpb.es.daw.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PlanoRepository planoRepository;

    public Page<Matricula> listar(Pageable pageable) {
        return matriculaRepository.findAll(pageable);
    }

    public Matricula buscarPorId(Long id) {
        return matriculaRepository.findById(id).orElse(null);
    }

    public Matricula salvar(MatriculaRequest request) {
        Aluno aluno = alunoRepository.findById(request.getAlunoId()).orElse(null);
        Plano plano = planoRepository.findById(request.getPlanoId()).orElse(null);

        if (aluno == null || plano == null) {
            return null;
        }

        Matricula matricula = new Matricula();
        matricula.setDataInicio(request.getDataInicio());
        matricula.setAluno(aluno);
        matricula.setPlano(plano);

        return matriculaRepository.save(matricula);
    }

    public Matricula atualizar(Long id, MatriculaRequest request) {
        Matricula matricula = buscarPorId(id);

        if (matricula == null) {
            return null;
        }

        Aluno aluno = alunoRepository.findById(request.getAlunoId()).orElse(null);
        Plano plano = planoRepository.findById(request.getPlanoId()).orElse(null);

        if (aluno == null || plano == null) {
            return null;
        }

        matricula.setDataInicio(request.getDataInicio());
        matricula.setAluno(aluno);
        matricula.setPlano(plano);

        return matriculaRepository.save(matricula);
    }

    public void excluir(Long id) {
        matriculaRepository.deleteById(id);
    }

    public List<Pagamento> buscarPagamentosDaMatricula(Long id) {
        Matricula matricula = matriculaRepository.buscarComPagamentos(id).orElse(null);

        if (matricula == null) {
            return List.of();
        }

        return matricula.getPagamentos();
    }
}