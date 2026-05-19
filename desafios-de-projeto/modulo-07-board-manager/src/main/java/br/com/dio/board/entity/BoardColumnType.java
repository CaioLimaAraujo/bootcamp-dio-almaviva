package br.com.dio.board.entity;

public enum BoardColumnType {
    INITIAL,    // 1ª coluna: ponto de entrada dos cards
    PENDING,    // Colunas intermediárias (0 ou mais)
    FINAL,      // Penúltima coluna: cards concluídos
    CANCELLED   // Última coluna: cards cancelados
}