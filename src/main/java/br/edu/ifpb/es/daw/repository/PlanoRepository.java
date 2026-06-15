package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Plano;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {

    Page<Plano> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}