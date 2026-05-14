package com.caio.acervoapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caio.acervoapi.model.Obra;
import com.caio.acervoapi.service.AcervoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final AcervoService service;

    @PostMapping
    public ResponseEntity<Obra> cadastrar(@RequestBody Obra obra) {
        return ResponseEntity.ok(service.salvar(obra));
    }

    @GetMapping
    public ResponseEntity<List<Obra>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Obra>> buscar(
            @RequestParam String tipo,
            @RequestParam String valor) {
        return ResponseEntity.ok(service.buscar(tipo, valor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}