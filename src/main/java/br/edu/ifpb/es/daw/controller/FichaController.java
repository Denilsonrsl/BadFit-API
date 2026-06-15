package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.dto.FichaRequest;
import br.edu.ifpb.es.daw.entity.Ficha;
import br.edu.ifpb.es.daw.service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    @Autowired
    private FichaService service;

    @GetMapping
    public Page<Ficha> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(PageRequest.of(page, size));
    }

    @PostMapping
    public Ficha salvar(@RequestBody FichaRequest request) {
        return service.salvar(request);
    }

    @GetMapping("/{id}")
    public Ficha buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Ficha atualizar(
            @PathVariable Long id,
            @RequestBody FichaRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Ficha> buscarPorAluno(@PathVariable Long alunoId) {
        return service.buscarPorAluno(alunoId);
    }
}