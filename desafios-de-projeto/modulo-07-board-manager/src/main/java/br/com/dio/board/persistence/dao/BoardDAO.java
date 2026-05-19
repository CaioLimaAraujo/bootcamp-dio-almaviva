package br.com.dio.board.persistence.dao;

import br.com.dio.board.entity.Board;
import br.com.dio.board.persistence.config.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDAO {

    public Board insert(Board board) throws SQLException {
        var sql = "INSERT INTO BOARD (name) VALUES (?)";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, board.getName());
            stmt.executeUpdate();
            try (var keys = stmt.getGeneratedKeys()) {
                if (keys.next()) board.setId(keys.getLong(1));
            }
        }
        return board;
    }

    public List<Board> findAll() throws SQLException {
        var sql = "SELECT id, name FROM BOARD ORDER BY id";
        var boards = new ArrayList<Board>();
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs   = stmt.executeQuery()) {
            while (rs.next()) {
                boards.add(new Board(rs.getLong("id"), rs.getString("name")));
            }
        }
        return boards;
    }

    public Optional<Board> findById(Long id) throws SQLException {
        var sql = "SELECT id, name FROM BOARD WHERE id = ?";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Board(rs.getLong("id"), rs.getString("name")));
                }
            }
        }
        return Optional.empty();
    }

    public void delete(Long id) throws SQLException {
        var sql = "DELETE FROM BOARD WHERE id = ?";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}