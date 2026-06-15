package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Aluno;
import br.edu.ifpb.es.daw.exception.ResourceNotFoundException;
import br.edu.ifpb.es.daw.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Page<Aluno> listar(String nome, Pageable pageable) {
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return repository.findAll(pageable);
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + id));
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno aluno = buscarPorId(id);

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setCpf(alunoAtualizado.getCpf());
        aluno.setDataNascimento(alunoAtualizado.getDataNascimento());

        return repository.save(aluno);
    }

    public void excluir(Long id) {
        Aluno aluno = buscarPorId(id);
        repository.delete(aluno);
    }
}