package br.com.dio.board.entity;

import java.time.LocalDateTime;

public class Card {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean blocked;
    private Long boardColumnId;

    public Card() {}

    public Card(Long id, String title, String description,
                LocalDateTime createdAt, boolean blocked, Long boardColumnId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.blocked = blocked;
        this.boardColumnId = boardColumnId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public Long getBoardColumnId() { return boardColumnId; }
    public void setBoardColumnId(Long boardColumnId) { this.boardColumnId = boardColumnId; }
}