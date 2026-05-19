package br.com.dio.board.service;

import java.sql.SQLException;
import java.util.List;

import br.com.dio.board.entity.Board;
import br.com.dio.board.entity.BoardColumn;
import br.com.dio.board.entity.BoardColumnType;
import br.com.dio.board.persistence.dao.BoardColumnDAO;
import br.com.dio.board.persistence.dao.BoardDAO;

public class BoardService {

    private final BoardDAO       boardDAO  = new BoardDAO();
    private final BoardColumnDAO columnDAO = new BoardColumnDAO();

    public Board createBoard(String name, List<String> columnNames) throws SQLException {
        if (columnNames.size() < 3) {
            throw new IllegalArgumentException("Um board precisa de pelo menos 3 colunas.");
        }

        var board = new Board();
        board.setName(name);
        boardDAO.insert(board);

        int order = 1;

        insertColumn(board.getId(), columnNames.get(0), order++, BoardColumnType.INITIAL);

        for (int i = 1; i < columnNames.size() - 2; i++) {
            insertColumn(board.getId(), columnNames.get(i), order++, BoardColumnType.PENDING);
        }

        insertColumn(board.getId(), columnNames.get(columnNames.size() - 2), order++, BoardColumnType.FINAL);

        insertColumn(board.getId(), columnNames.get(columnNames.size() - 1), order, BoardColumnType.CANCELLED);

        return loadFullBoard(board.getId());
    }

    public List<Board> listBoards() throws SQLException {
        return boardDAO.findAll();
    }

    public Board loadFullBoard(Long boardId) throws SQLException {
        var board = boardDAO.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado: " + boardId));
        board.setColumns(columnDAO.findByBoardId(boardId));
        return board;
    }

    public void deleteBoard(Long id) throws SQLException {
        boardDAO.delete(id);
    }

    private void insertColumn(Long boardId, String name, int order, BoardColumnType type) throws SQLException {
        var col = new BoardColumn();
        col.setName(name);
        col.setOrder(order);
        col.setType(type);
        col.setBoardId(boardId);
        columnDAO.insert(col);
    }
}