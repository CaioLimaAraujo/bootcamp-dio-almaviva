package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GameStatus;
import model.SudokuBoard;
import service.SudokuValidator;

public class SudokuFrame extends JFrame {

    private final SudokuBoard board;
    private final SudokuValidator validator;
    private final BoardPanel boardPanel;

    public SudokuFrame(SudokuBoard board, SudokuValidator validator) {
        this.board = board;
        this.validator = validator;
        this.boardPanel = new BoardPanel(board);

        setTitle("Sudoku — Bootcamp DIO/Almaviva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        add(boardPanel, BorderLayout.CENTER);
        add(buildNumberPanel(), BorderLayout.SOUTH);
        add(buildMenuPanel(), BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);

        board.startGame();
    }

    private JPanel buildNumberPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 9, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        for (int i = 1; i <= 9; i++) {
            final int number = i;
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(e -> placeNumber(number));
            panel.add(btn);
        }

        return panel;
    }

    private JPanel buildMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));

        JButton btnRemove  = new JButton("Remover");
        JButton btnClear   = new JButton("Limpar Jogo");
        JButton btnCheck   = new JButton("Verificar");
        JButton btnStatus  = new JButton("Status");
        JButton btnExit    = new JButton("Sair");

        btnRemove.addActionListener(e -> removeNumber());
        btnClear.addActionListener(e -> clearGame());
        btnCheck.addActionListener(e -> checkGame());
        btnStatus.addActionListener(e -> showStatus());
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnRemove);
        panel.add(btnClear);
        panel.add(btnCheck);
        panel.add(btnStatus);
        panel.add(btnExit);

        return panel;
    }

    private void placeNumber(int number) {
        int row = boardPanel.getSelectedRow();
        int col = boardPanel.getSelectedCol();

        if (row == -1 || col == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione uma célula no tabuleiro primeiro.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean placed = board.placeNumber(row, col, number);
        if (!placed) {
            JOptionPane.showMessageDialog(this,
                "Essa célula é fixa e não pode ser alterada.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
        }

        boardPanel.repaint();
        checkVictory();
    }

    private void removeNumber() {
        int row = boardPanel.getSelectedRow();
        int col = boardPanel.getSelectedCol();

        if (row == -1 || col == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione uma célula primeiro.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean removed = board.removeNumber(row, col);
        if (!removed) {
            JOptionPane.showMessageDialog(this,
                "Células fixas não podem ser removidas.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
        }

        boardPanel.repaint();
    }

    private void clearGame() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Deseja limpar todos os seus números?",
            "Limpar Jogo", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            board.clearUserNumbers();
            boardPanel.clearSelection();
        }
    }

    private void checkGame() {
        if (board.hasErrors()) {
            JOptionPane.showMessageDialog(this,
                "O tabuleiro tem erros! As células em vermelho indicam conflitos.",
                "Verificação", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Sem erros até agora! Continue assim.",
                "Verificação", JOptionPane.INFORMATION_MESSAGE);
        }
        boardPanel.repaint();
    }

    private void showStatus() {
        GameStatus status = board.getStatus();
        String message = switch (status) {
            case NOT_STARTED -> "O jogo ainda não foi iniciado.";
            case INCOMPLETE  -> "Jogo em andamento.";
            case COMPLETE    -> "Parabéns! O jogo foi concluído com sucesso!";
        };
        JOptionPane.showMessageDialog(this, message, "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkVictory() {
        if (board.getStatus() == GameStatus.COMPLETE) {
            JOptionPane.showMessageDialog(this,
                "Parabéns! Você concluiu o Sudoku!",
                "Vitória!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}