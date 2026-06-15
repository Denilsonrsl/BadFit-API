package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.entity.Plano;
import br.edu.ifpb.es.daw.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService service;

    @GetMapping
    public Page<Plano> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(nome, PageRequest.of(page, size));
    }

    @PostMapping
    public Plano salvar(@Valid @RequestBody Plano plano) {
        return service.salvar(plano);
    }

    @GetMapping("/{id}")
    public Plano buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Plano atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Plano plano
    ) {
        return service.atualizar(id, plano);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}