package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.entity.Aluno;
import br.edu.ifpb.es.daw.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public Page<Aluno> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(nome, PageRequest.of(page, size));
    }

    @PostMapping
    public Aluno salvar(@Valid @RequestBody Aluno aluno) {
        return service.salvar(aluno);
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Aluno aluno
    ) {
        return service.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}