package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    @Query("""
            SELECT m
            FROM Matricula m
            LEFT JOIN FETCH m.pagamentos
            WHERE m.id = :id
            """)
    Optional<Matricula> buscarComPagamentos(Long id);

}