package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.dto.ItemFichaRequest;
import br.edu.ifpb.es.daw.entity.Exercicio;
import br.edu.ifpb.es.daw.entity.Ficha;
import br.edu.ifpb.es.daw.entity.ItemFicha;
import br.edu.ifpb.es.daw.repository.ExercicioRepository;
import br.edu.ifpb.es.daw.repository.FichaRepository;
import br.edu.ifpb.es.daw.repository.ItemFichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemFichaService {

    @Autowired
    private ItemFichaRepository itemFichaRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Page<ItemFicha> listar(Pageable pageable) {
        return itemFichaRepository.findAll(pageable);
    }

    public ItemFicha buscarPorId(Long id) {
        return itemFichaRepository.findById(id).orElse(null);
    }

    public ItemFicha salvar(ItemFichaRequest request) {
        Ficha ficha = fichaRepository.findById(request.getFichaId()).orElse(null);
        Exercicio exercicio = exercicioRepository.findById(request.getExercicioId()).orElse(null);

        if (ficha == null || exercicio == null) {
            return null;
        }

        ItemFicha itemFicha = new ItemFicha();
        itemFicha.setCarga(request.getCarga());
        itemFicha.setSeries(request.getSeries());
        itemFicha.setRepeticoes(request.getRepeticoes());
        itemFicha.setFicha(ficha);
        itemFicha.setExercicio(exercicio);

        return itemFichaRepository.save(itemFicha);
    }

    public ItemFicha atualizar(Long id, ItemFichaRequest request) {
        ItemFicha itemFicha = buscarPorId(id);

        if (itemFicha == null) {
            return null;
        }

        Ficha ficha = fichaRepository.findById(request.getFichaId()).orElse(null);
        Exercicio exercicio = exercicioRepository.findById(request.getExercicioId()).orElse(null);

        if (ficha == null || exercicio == null) {
            return null;
        }

        itemFicha.setCarga(request.getCarga());
        itemFicha.setSeries(request.getSeries());
        itemFicha.setRepeticoes(request.getRepeticoes());
        itemFicha.setFicha(ficha);
        itemFicha.setExercicio(exercicio);

        return itemFichaRepository.save(itemFicha);
    }

    public void excluir(Long id) {
        itemFichaRepository.deleteById(id);
    }

    public List<ItemFicha> buscarPorFicha(Long fichaId) {
        return itemFichaRepository.findByFichaId(fichaId);
    }
}