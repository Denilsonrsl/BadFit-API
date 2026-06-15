package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.dto.FichaRequest;
import br.edu.ifpb.es.daw.entity.Aluno;
import br.edu.ifpb.es.daw.entity.Ficha;
import br.edu.ifpb.es.daw.entity.Instrutor;
import br.edu.ifpb.es.daw.repository.AlunoRepository;
import br.edu.ifpb.es.daw.repository.FichaRepository;
import br.edu.ifpb.es.daw.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    public Page<Ficha> listar(Pageable pageable) {
        return fichaRepository.findAll(pageable);
    }

    public Ficha buscarPorId(Long id) {
        return fichaRepository.findById(id).orElse(null);
    }

    public Ficha salvar(FichaRequest request) {
        Aluno aluno = alunoRepository.findById(request.getAlunoId()).orElse(null);
        Instrutor instrutor = instrutorRepository.findById(request.getInstrutorId()).orElse(null);

        if (aluno == null || instrutor == null) {
            return null;
        }

        Ficha ficha = new Ficha();
        ficha.setDataCriacao(request.getDataCriacao());
        ficha.setAluno(aluno);
        ficha.setInstrutor(instrutor);

        return fichaRepository.save(ficha);
    }

    public Ficha atualizar(Long id, FichaRequest request) {
        Ficha ficha = buscarPorId(id);

        if (ficha == null) {
            return null;
        }

        Aluno aluno = alunoRepository.findById(request.getAlunoId()).orElse(null);
        Instrutor instrutor = instrutorRepository.findById(request.getInstrutorId()).orElse(null);

        if (aluno == null || instrutor == null) {
            return null;
        }

        ficha.setDataCriacao(request.getDataCriacao());
        ficha.setAluno(aluno);
        ficha.setInstrutor(instrutor);

        return fichaRepository.save(ficha);
    }

    public void excluir(Long id) {
        fichaRepository.deleteById(id);
    }

    public List<Ficha> buscarPorAluno(Long alunoId) {
        return fichaRepository.findByAlunoId(alunoId);
    }
}