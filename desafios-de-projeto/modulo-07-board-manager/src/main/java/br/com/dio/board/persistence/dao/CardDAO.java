package br.com.dio.board.persistence.dao;

import br.com.dio.board.entity.Card;
import br.com.dio.board.persistence.config.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDAO {

    public Card insert(Card card) throws SQLException {
        var sql = "INSERT INTO CARD (title, description, board_column_id) VALUES (?, ?, ?)";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, card.getTitle());
            stmt.setString(2, card.getDescription());
            stmt.setLong(3, card.getBoardColumnId());
            stmt.executeUpdate();
            try (var keys = stmt.getGeneratedKeys()) {
                if (keys.next()) card.setId(keys.getLong(1));
            }
        }
        return card;
    }

    public List<Card> findByColumnId(Long columnId) throws SQLException {
        var sql = "SELECT id, title, description, created_at, blocked, board_column_id " +
                  "FROM CARD WHERE board_column_id = ?";
        var cards = new ArrayList<Card>();
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, columnId);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapCard(rs));
                }
            }
        }
        return cards;
    }

    public Optional<Card> findById(Long id) throws SQLException {
        var sql = "SELECT id, title, description, created_at, blocked, board_column_id " +
                  "FROM CARD WHERE id = ?";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(mapCard(rs));
            }
        }
        return Optional.empty();
    }

    public void updateColumn(Long cardId, Long newColumnId) throws SQLException {
        var sql = "UPDATE CARD SET board_column_id = ? WHERE id = ?";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, newColumnId);
            stmt.setLong(2, cardId);
            stmt.executeUpdate();
        }
    }

    public void updateBlocked(Long cardId, boolean blocked) throws SQLException {
        var sql = "UPDATE CARD SET blocked = ? WHERE id = ?";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, blocked);
            stmt.setLong(2, cardId);
            stmt.executeUpdate();
        }
    }

    private Card mapCard(ResultSet rs) throws SQLException {
        return new Card(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getBoolean("blocked"),
                rs.getLong("board_column_id")
        );
    }
}