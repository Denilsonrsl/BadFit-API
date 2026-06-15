package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaRepository extends JpaRepository<Ficha, Long> {

    List<Ficha> findByAlunoId(Long alunoId);

}