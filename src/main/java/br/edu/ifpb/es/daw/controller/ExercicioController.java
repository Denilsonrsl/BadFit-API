package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.entity.Exercicio;
import br.edu.ifpb.es.daw.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService service;

    @GetMapping
    public Page<Exercicio> listar(
            @RequestParam(required = false) String grupoMuscular,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(grupoMuscular, PageRequest.of(page, size));
    }

    @PostMapping
    public Exercicio salvar(@Valid @RequestBody Exercicio exercicio) {
        return service.salvar(exercicio);
    }

    @GetMapping("/{id}")
    public Exercicio buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Exercicio atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Exercicio exercicio
    ) {
        return service.atualizar(id, exercicio);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}