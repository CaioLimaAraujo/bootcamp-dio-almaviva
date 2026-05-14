package com.caio.acervoapi.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caio.acervoapi.model.Obra;
import com.caio.acervoapi.repository.ObraRepository;

@Component
public class BuscaPorGenero implements BuscaStrategy {

    private final ObraRepository repository;

    public BuscaPorGenero(ObraRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Obra> buscar(String valor) {
        return repository.findByGeneroContainingIgnoreCase(valor);
    }
}