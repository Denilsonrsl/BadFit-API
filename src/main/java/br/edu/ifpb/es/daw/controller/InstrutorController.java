package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.entity.Instrutor;
import br.edu.ifpb.es.daw.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @GetMapping
    public Page<Instrutor> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(nome, PageRequest.of(page, size));
    }

    @PostMapping
    public Instrutor salvar(@Valid @RequestBody Instrutor instrutor) {
        return service.salvar(instrutor);
    }

    @GetMapping("/{id}")
    public Instrutor buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Instrutor atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Instrutor instrutor
    ) {
        return service.atualizar(id, instrutor);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}