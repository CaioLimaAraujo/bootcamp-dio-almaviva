package com.caio.acervoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.acervoapi.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

    List<Obra> findByAutorContainingIgnoreCase(String autor);
    List<Obra> findByGeneroContainingIgnoreCase(String genero);
    List<Obra> findByTituloContainingIgnoreCase(String titulo);
}