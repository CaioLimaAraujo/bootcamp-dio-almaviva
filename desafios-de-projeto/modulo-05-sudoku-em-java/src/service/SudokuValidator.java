package service;

import model.SudokuBoard;

public class SudokuValidator {

    private final SudokuBoard board;

    public SudokuValidator(SudokuBoard board) {
        this.board = board;
    }

    public boolean isRowValid(int row) {
        boolean[] seen = new boolean[10];
        for (int col = 0; col < 9; col++) {
            int value = board.getCell(row, col).getValue();
            if (value == 0) continue;
            if (seen[value]) return false;
            seen[value] = true;
        }
        return true;
    }

    public boolean isColumnValid(int col) {
        boolean[] seen = new boolean[10];
        for (int row = 0; row < 9; row++) {
            int value = board.getCell(row, col).getValue();
            if (value == 0) continue;
            if (seen[value]) return false;
            seen[value] = true;
        }
        return true;
    }

    public boolean isBlockValid(int startRow, int startCol) {
        boolean[] seen = new boolean[10];
        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                int value = board.getCell(row, col).getValue();
                if (value == 0) continue;
                if (seen[value]) return false;
                seen[value] = true;
            }
        }
        return true;
    }

    public boolean isBoardValid() {
        for (int i = 0; i < 9; i++) {
            if (!isRowValid(i)) return false;
            if (!isColumnValid(i)) return false;
        }
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!isBlockValid(row, col)) return false;
            }
        }
        return true;
    }

    public boolean isBoardComplete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getCell(row, col).isEmpty()) return false;
            }
        }
        return isBoardValid();
    }
}