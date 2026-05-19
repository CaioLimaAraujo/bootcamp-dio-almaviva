USE board_db;

-- Tabela principal: boards
CREATE TABLE IF NOT EXISTS BOARD (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Colunas de cada board
-- col_order define a posição; type define o papel da coluna
CREATE TABLE IF NOT EXISTS BOARD_COLUMN (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    col_order INT NOT NULL,
    type      ENUM('INITIAL','PENDING','FINAL','CANCELLED') NOT NULL,
    board_id  BIGINT NOT NULL,
    FOREIGN KEY (board_id) REFERENCES BOARD(id) ON DELETE CASCADE
);

-- Cards que vivem dentro das colunas
CREATE TABLE IF NOT EXISTS CARD (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    blocked         BOOLEAN DEFAULT FALSE,
    board_column_id BIGINT NOT NULL,
    FOREIGN KEY (board_column_id) REFERENCES BOARD_COLUMN(id) ON DELETE CASCADE
);

-- Histórico de bloqueios de cada card
CREATE TABLE IF NOT EXISTS BLOCK (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_id        BIGINT NOT NULL,
    blocked_at     TIMESTAMP NOT NULL,
    block_reason   VARCHAR(500) NOT NULL,
    unblocked_at   TIMESTAMP,
    unblock_reason VARCHAR(500),
    FOREIGN KEY (card_id) REFERENCES CARD(id) ON DELETE CASCADE
);

-- Opcional (Requisitos 1, 2, 3): histórico de movimentação por coluna
CREATE TABLE IF NOT EXISTS CARD_COLUMN_HISTORY (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_id         BIGINT NOT NULL,
    board_column_id BIGINT NOT NULL,
    entered_at      TIMESTAMP NOT NULL,
    left_at         TIMESTAMP,
    FOREIGN KEY (card_id)         REFERENCES CARD(id)         ON DELETE CASCADE,
    FOREIGN KEY (board_column_id) REFERENCES BOARD_COLUMN(id) ON DELETE CASCADE
);