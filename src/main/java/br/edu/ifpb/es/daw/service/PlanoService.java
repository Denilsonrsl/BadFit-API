package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Plano;
import br.edu.ifpb.es.daw.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository repository;

    public Page<Plano> listar(String nome, Pageable pageable) {
        if (nome != null && !nome.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return repository.findAll(pageable);
    }

    public Plano salvar(Plano plano) {
        return repository.save(plano);
    }

    public Plano buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Plano atualizar(Long id, Plano planoAtualizado) {
        Plano plano = buscarPorId(id);

        if (plano == null) {
            return null;
        }

        plano.setNome(planoAtualizado.getNome());
        plano.setValor(planoAtualizado.getValor());
        plano.setDuracao(planoAtualizado.getDuracao());

        return repository.save(plano);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}