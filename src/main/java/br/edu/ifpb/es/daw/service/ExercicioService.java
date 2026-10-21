package br.edu.ifpb.es.daw.service;

import br.edu.ifpb.es.daw.entity.Exercicio;
import br.edu.ifpb.es.daw.repository.ExercicioRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ExercicioService {

    private static final Logger log = LoggerFactory.getLogger(ExercicioService.class);

    @Autowired
    private ExercicioRepository repository;

    // Cache-aside: chave = id do exercicio, valor = exercicio.
    // TTL de 5 minutos e no maximo 500 entradas.
    private final Cache<Long, Exercicio> cache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(5))
            .maximumSize(500)
            .build();

    public Page<Exercicio> listar(String grupoMuscular, Pageable pageable) {
        if (grupoMuscular != null && !grupoMuscular.isBlank()) {
            return repository.findByGrupoMuscularContainingIgnoreCase(grupoMuscular, pageable);
        }

        return repository.findAll(pageable);
    }

    public Exercicio salvar(Exercicio exercicio) {
        return repository.save(exercicio);
    }

    public Exercicio buscarPorId(Long id) {
        // 1. Procura no cache
        Exercicio emCache = cache.getIfPresent(id);
        if (emCache != null) {
            log.info("CACHE HIT exercicio id={}", id);
            return emCache;
        }

        // 2. Cache miss: consulta o banco
        log.info("CACHE MISS exercicio id={}", id);
        Exercicio exercicio = repository.findById(id).orElse(null);

        // 3. Guarda no cache (nao guarda null)
        if (exercicio != null) {
            cache.put(id, exercicio);
        }

        // 4. Retorna
        return exercicio;
    }

    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        // Busca direto no banco, para nao alterar o objeto que esta guardado no cache
        Exercicio exercicio = repository.findById(id).orElse(null);

        if (exercicio == null) {
            return null;
        }

        exercicio.setNome(exercicioAtualizado.getNome());
        exercicio.setGrupoMuscular(exercicioAtualizado.getGrupoMuscular());

        Exercicio salvo = repository.save(exercicio);

        // Invalida a entrada antiga para a proxima leitura buscar o dado novo
        cache.invalidate(id);
        log.info("CACHE INVALIDATE exercicio id={} (atualizar)", id);

        return salvo;
    }

    public void excluir(Long id) {
        repository.deleteById(id);

        cache.invalidate(id);
        log.info("CACHE INVALIDATE exercicio id={} (excluir)", id);
    }
}
