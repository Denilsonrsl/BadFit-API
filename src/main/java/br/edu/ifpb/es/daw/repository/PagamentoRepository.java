package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.Pagamento;
import br.edu.ifpb.es.daw.entity.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Page<Pagamento> findByStatus(StatusPagamento status, Pageable pageable);

    List<Pagamento> findByValorPagoBetween(Double min, Double max);

    @Query("SELECT COALESCE(SUM(p.valorPago), 0) FROM Pagamento p")
    Double totalArrecadado();
}