package br.com.dio.board.ui;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.dio.board.entity.Board;
import br.com.dio.board.service.BoardService;
import br.com.dio.board.service.CardService;

public class BoardMenu {

    private Board board;
    private final Scanner      scanner      = new Scanner(System.in);
    private final CardService  cardService  = new CardService();
    private final BoardService boardService = new BoardService();

    public BoardMenu(Board board, Scanner scanner) {
        this.board = board;
    }

    public void show() {
        var running = true;
        while (running) {
            refreshBoard();
            printBoard();
            System.out.println("""
                    1. Criar card
                    2. Mover card para próxima coluna
                    3. Cancelar card
                    4. Bloquear card
                    5. Desbloquear card
                    6. Fechar board""");
            System.out.print("Opção: ");

            try {
                switch (Integer.parseInt(scanner.nextLine().trim())) {
                    case 1 -> createCard();
                    case 2 -> moveCard();
                    case 3 -> cancelCard();
                    case 4 -> blockCard();
                    case 5 -> unblockCard();
                    case 6 -> running = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (SQLException e) {
                System.out.println("Erro de banco de dados: " + e.getMessage());
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private void refreshBoard() {
        try {
            board = boardService.loadFullBoard(board.getId());
            for (var col : board.getColumns()) {
                col.setCards(cardService.listByColumn(col.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar board: " + e.getMessage());
        }
    }

    private void printBoard() {
        System.out.println("\n╔═════════════════════════════════════╗");
        System.out.println("║  BOARD: " + board.getName());
        System.out.println("╚═════════════════════════════════════╝");
        for (var col : board.getColumns()) {
            System.out.printf("  [%-10s] %s (%d cards)%n",
                    col.getType(), col.getName(), col.getCards().size());
            for (var card : col.getCards()) {
                System.out.printf("       #%d — %s%s%n",
                        card.getId(),
                        card.getTitle(),
                        card.isBlocked() ? "  🔒 BLOQUEADO" : "");
            }
        }
        System.out.println();
    }

    private void createCard() throws SQLException {
        System.out.print("Título: ");
        var title = scanner.nextLine().trim();
        System.out.print("Descrição: ");
        var desc = scanner.nextLine().trim();
        var initial = board.getInitialColumn();
        cardService.createCard(title, desc, initial.getId());
        System.out.println("✔ Card criado em: " + initial.getName());
    }

    private void moveCard() throws SQLException {
        System.out.print("ID do card: ");
        cardService.moveCard(Long.parseLong(scanner.nextLine().trim()), board);
    }

    private void cancelCard() throws SQLException {
        System.out.print("ID do card: ");
        cardService.cancelCard(Long.parseLong(scanner.nextLine().trim()), board);
    }

    private void blockCard() throws SQLException {
        System.out.print("ID do card: ");
        var id = Long.parseLong(scanner.nextLine().trim());
        System.out.print("Motivo do bloqueio: ");
        cardService.blockCard(id, scanner.nextLine().trim());
    }

    private void unblockCard() throws SQLException {
        System.out.print("ID do card: ");
        var id = Long.parseLong(scanner.nextLine().trim());
        System.out.print("Motivo do desbloqueio: ");
        cardService.unblockCard(id, scanner.nextLine().trim());
    }
}