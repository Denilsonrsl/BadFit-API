package br.edu.ifpb.es.daw.controller;

import br.edu.ifpb.es.daw.entity.Pagamento;
import br.edu.ifpb.es.daw.entity.StatusPagamento;
import br.edu.ifpb.es.daw.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<Pagamento> listar(
            @RequestParam(required = false) StatusPagamento status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.listar(status, PageRequest.of(page, size));
    }

    @PostMapping
    public Pagamento salvar(@RequestBody Pagamento pagamento) {
        return service.salvar(pagamento);
    }

    @GetMapping("/{id}")
    public Pagamento buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pagamento atualizar(
            @PathVariable Long id,
            @RequestBody Pagamento pagamento
    ) {
        return service.atualizar(id, pagamento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/faixa")
    public List<Pagamento> buscarPorFaixaValor(
            @RequestParam Double min,
            @RequestParam Double max
    ) {
        return service.buscarPorFaixaValor(min, max);
    }

    @GetMapping("/total-arrecadado")
    public Map<String, Double> totalArrecadado() {
        return service.totalArrecadado();
    }
}