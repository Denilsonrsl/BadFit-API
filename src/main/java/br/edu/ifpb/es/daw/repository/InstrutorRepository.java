package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Page<Instrutor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}