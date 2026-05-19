package br.com.dio.board.persistence.dao;

import br.com.dio.board.entity.BlockRecord;
import br.com.dio.board.persistence.config.ConnectionConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockDAO {

    public void insert(BlockRecord block) throws SQLException {
        var sql = "INSERT INTO BLOCK (card_id, blocked_at, block_reason) VALUES (?, ?, ?)";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, block.getCardId());
            stmt.setTimestamp(2, Timestamp.valueOf(block.getBlockedAt()));
            stmt.setString(3, block.getBlockReason());
            stmt.executeUpdate();
            try (var keys = stmt.getGeneratedKeys()) {
                if (keys.next()) block.setId(keys.getLong(1));
            }
        }
    }

    public void unblock(Long cardId, String reason, LocalDateTime unblockedAt) throws SQLException {
        var sql = "UPDATE BLOCK SET unblocked_at = ?, unblock_reason = ? " +
                  "WHERE card_id = ? AND unblocked_at IS NULL";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(unblockedAt));
            stmt.setString(2, reason);
            stmt.setLong(3, cardId);
            stmt.executeUpdate();
        }
    }

    public List<BlockRecord> findByCardId(Long cardId) throws SQLException {
        var sql = "SELECT id, card_id, blocked_at, block_reason, unblocked_at, unblock_reason " +
                  "FROM BLOCK WHERE card_id = ? ORDER BY blocked_at";
        var blocks = new ArrayList<BlockRecord>();
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cardId);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) blocks.add(mapBlock(rs));
            }
        }
        return blocks;
    }

    public Optional<BlockRecord> findActiveBlock(Long cardId) throws SQLException {
        var sql = "SELECT id, card_id, blocked_at, block_reason FROM BLOCK " +
                  "WHERE card_id = ? AND unblocked_at IS NULL";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cardId);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(mapBlock(rs));
            }
        }
        return Optional.empty();
    }

    private BlockRecord mapBlock(ResultSet rs) throws SQLException {
        var b = new BlockRecord();
        b.setId(rs.getLong("id"));
        b.setCardId(rs.getLong("card_id"));
        b.setBlockedAt(rs.getTimestamp("blocked_at").toLocalDateTime());
        b.setBlockReason(rs.getString("block_reason"));
        var unblocked = rs.getTimestamp("unblocked_at");
        if (unblocked != null) b.setUnblockedAt(unblocked.toLocalDateTime());
        b.setUnblockReason(rs.getString("unblock_reason"));
        return b;
    }
}