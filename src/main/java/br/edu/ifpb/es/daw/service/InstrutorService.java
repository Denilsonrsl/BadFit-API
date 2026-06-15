package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Instrutor;
import br.edu.ifpb.es.daw.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository repository;

    public Page<Instrutor> listar(String nome, Pageable pageable) {
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return repository.findAll(pageable);
    }

    public Instrutor salvar(Instrutor instrutor) {
        return repository.save(instrutor);
    }

    public Instrutor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Instrutor atualizar(Long id, Instrutor instrutorAtualizado) {
        Instrutor instrutor = buscarPorId(id);

        if (instrutor == null) {
            return null;
        }

        instrutor.setNome(instrutorAtualizado.getNome());
        instrutor.setCpf(instrutorAtualizado.getCpf());

        return repository.save(instrutor);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}