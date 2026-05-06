package model;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private final Cell[][] grid;
    private GameStatus status;

    public SudokuBoard() {
    this.grid = new Cell[9][9];
    this.status = GameStatus.NOT_STARTED;
}

public void setCell(int row, int col, int value, boolean fixed) {
    int displayValue = fixed ? value : 0;
    grid[row][col] = new Cell(displayValue, fixed, value);
}

    public void startGame() {
        this.status = GameStatus.INCOMPLETE;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean placeNumber(int row, int col, int value) {
        Cell cell = grid[row][col];
        if (cell.isFixed()) return false;
        cell.setValue(value);
        updateStatus();
        return true;
    }

    public boolean removeNumber(int row, int col) {
        Cell cell = grid[row][col];
        if (cell.isFixed()) return false;
        cell.clear();
        updateStatus();
        return true;
    }

    public void clearUserNumbers() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col].clear();
            }
        }
        updateStatus();
    }

    public boolean hasErrors() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = grid[row][col];
                if (!cell.isEmpty()) {
                    if (!isValidPlacement(row, col, cell.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidPlacement(int row, int col, int value) {
        for (int c = 0; c < 9; c++) {
            if (c != col && grid[row][c].getValue() == value) return false;
        }
        for (int r = 0; r < 9; r++) {
            if (r != row && grid[r][col].getValue() == value) return false;
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && grid[r][c].getValue() == value) return false;
            }
        }
        return true;
    }

    private void updateStatus() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col].isEmpty()) {
                    this.status = GameStatus.INCOMPLETE;
                    return;
                }
            }
        }
        this.status = hasErrors() ? GameStatus.INCOMPLETE : GameStatus.COMPLETE;
    }

    public List<int[]> getErrors() {
        List<int[]> errors = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = grid[row][col];
                if (!cell.isEmpty() && !isValidPlacement(row, col, cell.getValue())) {
                    errors.add(new int[]{row, col});
                }
            }
        }
        return errors;
    }
}