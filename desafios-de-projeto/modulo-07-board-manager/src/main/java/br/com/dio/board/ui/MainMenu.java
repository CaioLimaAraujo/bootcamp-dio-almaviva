package br.com.dio.board.ui;

import br.com.dio.board.service.BoardService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private final BoardService boardService = new BoardService();
    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        var running = true;
        while (running) {
            System.out.println("""

                    ╔══════════════════════════════╗
                    ║     BOARD MANAGER — DIO      ║
                    ╚══════════════════════════════╝
                    1. Criar novo board
                    2. Selecionar board
                    3. Excluir board
                    4. Sair""");
            System.out.print("Opção: ");

            try {
                switch (Integer.parseInt(scanner.nextLine().trim())) {
                    case 1 -> createBoard();
                    case 2 -> selectBoard();
                    case 3 -> deleteBoard();
                    case 4 -> { running = false; System.out.println("Até mais!"); }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (SQLException e) {
                System.out.println("Erro de banco de dados: " + e.getMessage());
            }
        }
    }

    private void createBoard() throws SQLException {
        System.out.print("Nome do board: ");
        var name = scanner.nextLine().trim();

        System.out.print("Quantas colunas PENDENTES? (mínimo 0): ");
        int pendingCount = 0;
        try { pendingCount = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Usando 0."); }

        var columnNames = new ArrayList<String>();

        System.out.print("Nome da coluna INICIAL: ");
        columnNames.add(scanner.nextLine().trim());

        for (int i = 1; i <= pendingCount; i++) {
            System.out.print("Nome da coluna PENDENTE " + i + ": ");
            columnNames.add(scanner.nextLine().trim());
        }

        System.out.print("Nome da coluna FINAL: ");
        columnNames.add(scanner.nextLine().trim());

        System.out.print("Nome da coluna de CANCELAMENTO: ");
        columnNames.add(scanner.nextLine().trim());

        var board = boardService.createBoard(name, columnNames);
        System.out.println("✔ Board '" + board.getName() + "' criado! ID: " + board.getId());
    }

    private void selectBoard() throws SQLException {
        var boards = boardService.listBoards();
        if (boards.isEmpty()) { System.out.println("Nenhum board encontrado."); return; }

        System.out.println("\n— Boards disponíveis —");
        boards.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getName()));
        System.out.print("ID do board: ");

        try {
            var id        = Long.parseLong(scanner.nextLine().trim());
            var fullBoard = boardService.loadFullBoard(id);
            new BoardMenu(fullBoard, scanner).show();
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBoard() throws SQLException {
        var boards = boardService.listBoards();
        if (boards.isEmpty()) { System.out.println("Nenhum board para excluir."); return; }

        System.out.println("\n— Selecione o board para excluir —");
        boards.forEach(b -> System.out.println("[" + b.getId() + "] " + b.getName()));
        System.out.print("ID do board: ");

        try {
            var id = Long.parseLong(scanner.nextLine().trim());
            boardService.deleteBoard(id);
            System.out.println("✔ Board excluído com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}