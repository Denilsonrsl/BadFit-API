package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.dto.MatriculaRequest;
import br.edu.ifpb.es.daw.entity.Matricula;
import br.edu.ifpb.es.daw.entity.Pagamento;
import br.edu.ifpb.es.daw.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @GetMapping
    public Page<Matricula> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(PageRequest.of(page, size));
    }

    @PostMapping
    public Matricula salvar(@RequestBody MatriculaRequest request) {
        return service.salvar(request);
    }

    @GetMapping("/{id}")
    public Matricula buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Matricula atualizar(
            @PathVariable Long id,
            @RequestBody MatriculaRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/{id}/pagamentos")
    public List<Pagamento> buscarPagamentosDaMatricula(@PathVariable Long id) {
        return service.buscarPagamentosDaMatricula(id);
    }
}