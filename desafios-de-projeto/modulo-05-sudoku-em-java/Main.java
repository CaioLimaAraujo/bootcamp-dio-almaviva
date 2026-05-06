import model.SudokuBoard;
import service.SudokuValidator;
import view.SudokuFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        for (String arg : args) {
            String[] parts = arg.split(",");
            if (parts.length != 4) {
                System.err.println("Argumento inválido ignorado: " + arg);
                continue;
            }
            try {
                int col    = Integer.parseInt(parts[0].trim());
                int row    = Integer.parseInt(parts[1].trim());
                int value  = Integer.parseInt(parts[2].trim());
                boolean fixed = Boolean.parseBoolean(parts[3].trim());

                if (row < 0 || row > 8 || col < 0 || col > 8 || value < 1 || value > 9) {
                    System.err.println("Valores fora do intervalo ignorados: " + arg);
                    continue;
                }

                board.setCell(row, col, value, fixed);

            } catch (NumberFormatException e) {
                System.err.println("Argumento não numérico ignorado: " + arg);
            }
        }

        board.startGame();

        SudokuValidator validator = new SudokuValidator(board);

        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame(board, validator);
            frame.setVisible(true);
        });
    }
}