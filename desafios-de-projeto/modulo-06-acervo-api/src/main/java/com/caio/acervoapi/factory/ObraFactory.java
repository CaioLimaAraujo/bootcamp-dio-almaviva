package com.caio.acervoapi.factory;

import org.springframework.stereotype.Component;

import com.caio.acervoapi.model.Ensaio;
import com.caio.acervoapi.model.Livro;
import com.caio.acervoapi.model.Obra;
import com.caio.acervoapi.model.VideoEnsaio;

@Component
public class ObraFactory {

    public Obra criar(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "LIVRO"       -> new Livro();
            case "ENSAIO"      -> new Ensaio();
            case "VIDEO_ENSAIO" -> new VideoEnsaio();
            default -> throw new IllegalArgumentException("Tipo de obra desconhecido: " + tipo);
        };
    }
}