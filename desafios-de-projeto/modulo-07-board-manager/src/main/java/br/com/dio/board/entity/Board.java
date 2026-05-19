package br.com.dio.board.entity;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Long id;
    private String name;
    private List<BoardColumn> columns = new ArrayList<>();

    public Board() {}

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BoardColumn getInitialColumn() {
        return columns.stream()
                .filter(c -> c.getType() == BoardColumnType.INITIAL)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Board sem coluna inicial."));
    }

    public BoardColumn getCancelledColumn() {
        return columns.stream()
                .filter(c -> c.getType() == BoardColumnType.CANCELLED)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Board sem coluna de cancelamento."));
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<BoardColumn> getColumns() { return columns; }
    public void setColumns(List<BoardColumn> columns) { this.columns = columns; }
}