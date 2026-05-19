package br.com.dio.board.persistence.dao;

import br.com.dio.board.entity.BoardColumn;
import br.com.dio.board.entity.BoardColumnType;
import br.com.dio.board.persistence.config.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardColumnDAO {

    public BoardColumn insert(BoardColumn column) throws SQLException {
        var sql = "INSERT INTO BOARD_COLUMN (name, col_order, type, board_id) VALUES (?, ?, ?, ?)";
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, column.getName());
            stmt.setInt(2, column.getOrder());
            stmt.setString(3, column.getType().name());
            stmt.setLong(4, column.getBoardId());
            stmt.executeUpdate();
            try (var keys = stmt.getGeneratedKeys()) {
                if (keys.next()) column.setId(keys.getLong(1));
            }
        }
        return column;
    }

    public List<BoardColumn> findByBoardId(Long boardId) throws SQLException {
        var sql = "SELECT id, name, col_order, type, board_id " +
                  "FROM BOARD_COLUMN WHERE board_id = ? ORDER BY col_order";
        var columns = new ArrayList<BoardColumn>();
        try (var conn = ConnectionConfig.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, boardId);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    columns.add(new BoardColumn(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("col_order"),
                            BoardColumnType.valueOf(rs.getString("type")),
                            rs.getLong("board_id")
                    ));
                }
            }
        }
        return columns;
    }
}