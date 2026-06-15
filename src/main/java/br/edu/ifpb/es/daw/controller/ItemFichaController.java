package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.dto.ItemFichaRequest;
import br.edu.ifpb.es.daw.entity.ItemFicha;
import br.edu.ifpb.es.daw.service.ItemFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-ficha")
public class ItemFichaController {

    @Autowired
    private ItemFichaService service;

    @GetMapping
    public Page<ItemFicha> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(PageRequest.of(page, size));
    }

    @PostMapping
    public ItemFicha salvar(@RequestBody ItemFichaRequest request) {
        return service.salvar(request);
    }

    @GetMapping("/{id}")
    public ItemFicha buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ItemFicha atualizar(
            @PathVariable Long id,
            @RequestBody ItemFichaRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/ficha/{fichaId}")
    public List<ItemFicha> buscarPorFicha(@PathVariable Long fichaId) {
        return service.buscarPorFicha(fichaId);
    }
}