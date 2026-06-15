package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Exercicio;
import br.edu.ifpb.es.daw.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository repository;

    public Page<Exercicio> listar(String grupoMuscular, Pageable pageable) {
        if (grupoMuscular != null && !grupoMuscular.isBlank()) {
            return repository.findByGrupoMuscularContainingIgnoreCase(grupoMuscular, pageable);
        }

        return repository.findAll(pageable);
    }

    public Exercicio salvar(Exercicio exercicio) {
        return repository.save(exercicio);
    }

    public Exercicio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        Exercicio exercicio = buscarPorId(id);

        if (exercicio == null) {
            return null;
        }

        exercicio.setNome(exercicioAtualizado.getNome());
        exercicio.setGrupoMuscular(exercicioAtualizado.getGrupoMuscular());

        return repository.save(exercicio);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}