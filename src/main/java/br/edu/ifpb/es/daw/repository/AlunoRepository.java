package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeContainingIgnoreCase(
            String nome,
            Pageable pageable
    );

}