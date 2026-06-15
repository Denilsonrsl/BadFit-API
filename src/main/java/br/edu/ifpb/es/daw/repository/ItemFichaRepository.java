package br.edu.ifpb.es.daw.repository;

import br.edu.ifpb.es.daw.entity.ItemFicha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemFichaRepository extends JpaRepository<ItemFicha, Long> {

    List<ItemFicha> findByFichaId(Long fichaId);

}