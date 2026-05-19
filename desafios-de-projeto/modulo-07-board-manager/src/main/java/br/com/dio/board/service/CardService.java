package br.com.dio.board.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import br.com.dio.board.entity.BlockRecord;
import br.com.dio.board.entity.Board;
import br.com.dio.board.entity.BoardColumn;
import br.com.dio.board.entity.BoardColumnType;
import br.com.dio.board.entity.Card;
import br.com.dio.board.persistence.dao.BlockDAO;
import br.com.dio.board.persistence.dao.CardDAO;

public class CardService {

    private final CardDAO  cardDAO  = new CardDAO();
    private final BlockDAO blockDAO = new BlockDAO();

    public Card createCard(String title, String description, Long initialColumnId) throws SQLException {
        var card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setBoardColumnId(initialColumnId);
        return cardDAO.insert(card);
    }

    public void moveCard(Long cardId, Board board) throws SQLException {
        var card = findCardOrThrow(cardId);

        if (card.isBlocked()) throw new IllegalStateException("Card bloqueado! Desbloqueie antes de mover.");

        var columns = board.getColumns();
        int idx = findColumnIndex(columns, card.getBoardColumnId());

        var currentColumn = columns.get(idx);
        if (currentColumn.getType() == BoardColumnType.FINAL)
            throw new IllegalStateException("Card já está na coluna final.");
        if (currentColumn.getType() == BoardColumnType.CANCELLED)
            throw new IllegalStateException("Card cancelado não pode ser movido.");

        var nextColumn = columns.get(idx + 1);
        if (nextColumn.getType() == BoardColumnType.CANCELLED) {
            throw new IllegalStateException("Use a opção 'Cancelar card' para enviar ao cancelamento.");
        }

        cardDAO.updateColumn(cardId, nextColumn.getId());
        System.out.println("✔ Card movido para: " + nextColumn.getName());
    }

    public void cancelCard(Long cardId, Board board) throws SQLException {
        var card = findCardOrThrow(cardId);

        if (card.isBlocked()) throw new IllegalStateException("Card bloqueado! Desbloqueie antes de cancelar.");

        var columns = board.getColumns();
        int idx     = findColumnIndex(columns, card.getBoardColumnId());
        var current = columns.get(idx);

        if (current.getType() == BoardColumnType.FINAL)
            throw new IllegalStateException("Card concluído não pode ser cancelado.");
        if (current.getType() == BoardColumnType.CANCELLED)
            throw new IllegalStateException("Card já está cancelado.");

        var cancelledColumn = board.getCancelledColumn();
        cardDAO.updateColumn(cardId, cancelledColumn.getId());
        System.out.println("✔ Card cancelado e movido para: " + cancelledColumn.getName());
    }

    public void blockCard(Long cardId, String reason) throws SQLException {
        var card = findCardOrThrow(cardId);
        if (card.isBlocked()) throw new IllegalStateException("Card já está bloqueado.");

        var block = new BlockRecord();
        block.setCardId(cardId);
        block.setBlockedAt(LocalDateTime.now());
        block.setBlockReason(reason);

        blockDAO.insert(block);
        cardDAO.updateBlocked(cardId, true);
        System.out.println("✔ Card bloqueado com sucesso.");
    }

    public void unblockCard(Long cardId, String reason) throws SQLException {
        var card = findCardOrThrow(cardId);
        if (!card.isBlocked()) throw new IllegalStateException("Card não está bloqueado.");

        blockDAO.unblock(cardId, reason, LocalDateTime.now());
        cardDAO.updateBlocked(cardId, false);
        System.out.println("✔ Card desbloqueado com sucesso.");
    }

    public List<Card> listByColumn(Long columnId) throws SQLException {
        return cardDAO.findByColumnId(columnId);
    }

    private Card findCardOrThrow(Long cardId) throws SQLException {
        return cardDAO.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Card não encontrado: " + cardId));
    }

    private int findColumnIndex(List<BoardColumn> columns, Long columnId) {
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getId().equals(columnId)) return i;
        }
        throw new IllegalStateException("Coluna do card não encontrada no board.");
    }
}