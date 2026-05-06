package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import model.Cell;
import model.SudokuBoard;

public class BoardPanel extends JPanel {

    private static final int CELL_SIZE = 60;
    private static final int BOARD_SIZE = CELL_SIZE * 9;

    private final SudokuBoard board;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public BoardPanel(SudokuBoard board) {
        this.board = board;
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / CELL_SIZE;
                int row = e.getY() / CELL_SIZE;
                if (row >= 0 && row < 9 && col >= 0 && col < 9) {
                    selectedRow = row;
                    selectedCol = col;
                    repaint();
                }
            }
        });
    }

    public int getSelectedRow() { return selectedRow; }
    public int getSelectedCol() { return selectedCol; }

    public void clearSelection() {
        selectedRow = -1;
        selectedCol = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<int[]> errors = board.getErrors();

        drawCells(g2, errors);
        drawGrid(g2);
    }

    private void drawCells(Graphics2D g2, List<int[]> errors) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = board.getCell(row, col);
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Cor de fundo da célula
                if (row == selectedRow && col == selectedCol) {
                    g2.setColor(new Color(173, 216, 230)); // azul claro — selecionada
                } else if (isError(errors, row, col)) {
                    g2.setColor(new Color(255, 182, 193)); // vermelho claro — erro
                } else if (cell.isFixed()) {
                    g2.setColor(new Color(220, 220, 220)); // cinza — fixa
                } else {
                    g2.setColor(Color.WHITE);
                }

                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                // Número da célula
                if (!cell.isEmpty()) {
                    g2.setColor(cell.isFixed() ? Color.BLACK : new Color(0, 0, 180));
                    g2.setFont(new Font("Arial", Font.BOLD, 24));
                    FontMetrics fm = g2.getFontMetrics();
                    String text = String.valueOf(cell.getValue());
                    int textX = x + (CELL_SIZE - fm.stringWidth(text)) / 2;
                    int textY = y + (CELL_SIZE - fm.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(text, textX, textY);
                }
            }
        }
    }

    private void drawGrid(Graphics2D g2) {
        for (int i = 0; i <= 9; i++) {
            if (i % 3 == 0) {
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.BLACK);
            } else {
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.GRAY);
            }
            g2.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, BOARD_SIZE);
            g2.drawLine(0, i * CELL_SIZE, BOARD_SIZE, i * CELL_SIZE);
        }
    }

    private boolean isError(List<int[]> errors, int row, int col) {
        for (int[] error : errors) {
            if (error[0] == row && error[1] == col) return true;
        }
        return false;
    }
}