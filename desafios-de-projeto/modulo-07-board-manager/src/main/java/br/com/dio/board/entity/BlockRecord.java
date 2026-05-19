package br.com.dio.board.entity;

import java.time.LocalDateTime;

public class BlockRecord {

    private Long id;
    private Long cardId;
    private LocalDateTime blockedAt;
    private String blockReason;
    private LocalDateTime unblockedAt;
    private String unblockReason;

    public BlockRecord() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCardId() { return cardId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }
    public LocalDateTime getBlockedAt() { return blockedAt; }
    public void setBlockedAt(LocalDateTime blockedAt) { this.blockedAt = blockedAt; }
    public String getBlockReason() { return blockReason; }
    public void setBlockReason(String blockReason) { this.blockReason = blockReason; }
    public LocalDateTime getUnblockedAt() { return unblockedAt; }
    public void setUnblockedAt(LocalDateTime unblockedAt) { this.unblockedAt = unblockedAt; }
    public String getUnblockReason() { return unblockReason; }
    public void setUnblockReason(String unblockReason) { this.unblockReason = unblockReason; }
}