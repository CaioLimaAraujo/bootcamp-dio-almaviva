package br.com.dio.board.entity;

import java.util.ArrayList;
import java.util.List;

public class BoardColumn {

    private Long id;
    private String name;
    private int order;
    private BoardColumnType type;
    private Long boardId;
    private List<Card> cards = new ArrayList<>();

    public BoardColumn() {}

    public BoardColumn(Long id, String name, int order, BoardColumnType type, Long boardId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.type = type;
        this.boardId = boardId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
    public BoardColumnType getType() { return type; }
    public void setType(BoardColumnType type) { this.type = type; }
    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }
    public List<Card> getCards() { return cards; }
    public void setCards(List<Card> cards) { this.cards = cards; }
}