package com.caio.acervoapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.caio.acervoapi.factory.ObraFactory;
import com.caio.acervoapi.model.Obra;
import com.caio.acervoapi.repository.ObraRepository;
import com.caio.acervoapi.strategy.BuscaPorAutor;
import com.caio.acervoapi.strategy.BuscaPorGenero;
import com.caio.acervoapi.strategy.BuscaStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcervoService {

    private final ObraRepository repository;
    private final ObraFactory factory;
    private final BuscaPorAutor buscaPorAutor;
    private final BuscaPorGenero buscaPorGenero;

    public Obra salvar(Obra obra) {
        return repository.save(obra);
    }

    public List<Obra> listarTodas() {
        return repository.findAll();
    }

    public Obra buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Obra> buscar(String tipo, String valor) {
        BuscaStrategy strategy = switch (tipo.toLowerCase()) {
            case "autor"  -> buscaPorAutor;
            case "genero" -> buscaPorGenero;
            default -> throw new IllegalArgumentException("Tipo de busca inválido: " + tipo);
        };
        return strategy.buscar(valor);
    }

    public Obra criarObra(String tipo) {
        return factory.criar(tipo);
    }
}