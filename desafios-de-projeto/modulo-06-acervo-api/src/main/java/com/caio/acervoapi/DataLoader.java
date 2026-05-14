package com.caio.acervoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.caio.acervoapi.model.Ensaio;
import com.caio.acervoapi.model.Livro;
import com.caio.acervoapi.model.VideoEnsaio;
import com.caio.acervoapi.repository.ObraRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ObraRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Livro livro1 = new Livro();
        livro1.setTitulo("O Processo");
        livro1.setAutor("Franz Kafka");
        livro1.setGenero("Romance");
        livro1.setAno(1925);
        livro1.setDescricao("Um homem é preso sem saber o motivo.");
        livro1.setIsbn("978-8535922740");
        livro1.setEditora("Companhia das Letras");

        Livro livro2 = new Livro();
        livro2.setTitulo("Admirável Mundo Novo");
        livro2.setAutor("Aldous Huxley");
        livro2.setGenero("Ficção Científica");
        livro2.setAno(1932);
        livro2.setDescricao("Uma distopia sobre controle social e felicidade artificial.");
        livro2.setIsbn("978-8535909555");
        livro2.setEditora("Globo Livros");

        Ensaio ensaio1 = new Ensaio();
        ensaio1.setTitulo("A Indústria Cultural");
        ensaio1.setAutor("Theodor Adorno");
        ensaio1.setGenero("Filosofia");
        ensaio1.setAno(1947);
        ensaio1.setDescricao("Crítica à cultura de massa e à sua função ideológica.");
        ensaio1.setPublicacao("Dialética do Esclarecimento");

        VideoEnsaio video1 = new VideoEnsaio();
        video1.setTitulo("Por que Kafka ainda é relevante?");
        video1.setAutor("Canal Filosofia Pop");
        video1.setGenero("Literatura");
        video1.setAno(2022);
        video1.setDescricao("Uma análise da obra kafkiana e sua relação com o mundo contemporâneo.");
        video1.setUrl("https://youtube.com/exemplo");
        video1.setDuracaoMinutos(25);

        repository.save(livro1);
        repository.save(livro2);
        repository.save(ensaio1);
        repository.save(video1);

        System.out.println("✅ Acervo populado com sucesso!");
    }
}