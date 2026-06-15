package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Exercicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    Page<Exercicio> findByGrupoMuscularContainingIgnoreCase(
            String grupoMuscular,
            Pageable pageable
    );

}