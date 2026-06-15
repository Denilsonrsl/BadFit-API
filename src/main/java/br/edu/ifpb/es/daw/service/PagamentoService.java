package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Pagamento;
import br.edu.ifpb.es.daw.entity.StatusPagamento;
import br.edu.ifpb.es.daw.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Page<Pagamento> listar(StatusPagamento status, Pageable pageable) {
        if (status != null) {
            return repository.findByStatus(status, pageable);
        }

        return repository.findAll(pageable);
    }

    public Pagamento salvar(Pagamento pagamento) {
        return repository.save(pagamento);
    }

    public Pagamento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pagamento atualizar(Long id, Pagamento pagamentoAtualizado) {
        Pagamento pagamento = buscarPorId(id);

        if (pagamento == null) {
            return null;
        }

        pagamento.setDataPagamento(pagamentoAtualizado.getDataPagamento());
        pagamento.setDataVencimento(pagamentoAtualizado.getDataVencimento());
        pagamento.setValorPago(pagamentoAtualizado.getValorPago());
        pagamento.setStatus(pagamentoAtualizado.getStatus());
        pagamento.setMatricula(pagamentoAtualizado.getMatricula());

        return repository.save(pagamento);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Pagamento> buscarPorFaixaValor(Double min, Double max) {
        return repository.findByValorPagoBetween(min, max);
    }

    public Map<String, Double> totalArrecadado() {
        return Map.of("total", repository.totalArrecadado());
    }
}