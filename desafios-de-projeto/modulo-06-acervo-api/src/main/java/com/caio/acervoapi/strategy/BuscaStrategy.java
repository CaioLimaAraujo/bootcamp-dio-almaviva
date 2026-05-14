package com.caio.acervoapi.strategy;

import java.util.List;

import com.caio.acervoapi.model.Obra;

public interface BuscaStrategy {
    List<Obra> buscar(String valor);
}