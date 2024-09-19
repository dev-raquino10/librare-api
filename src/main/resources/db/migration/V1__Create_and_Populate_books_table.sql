-- Criação das tabelas
CREATE TABLE IF NOT EXISTS authors (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    id VARCHAR PRIMARY KEY,
    title VARCHAR NOT NULL,
    authors TEXT[] NOT NULL,
    genres TEXT[] NOT NULL,
    publish_date VARCHAR(255),
    isbn BIGINT,
    number_of_pages INTEGER  -- Ajustado para refletir a nomenclatura correta
);

-- Criação de índices
CREATE INDEX IF NOT EXISTS idx_authors_name ON authors (name);
CREATE INDEX IF NOT EXISTS idx_genres_name ON genres (name);

-- Inserção de dados exemplo
INSERT INTO authors (id, name) VALUES
('OL1A', 'J.K. Rowling'),
('OL2A', 'George R.R. Martin');

INSERT INTO genres (id, name) VALUES
('OL1G', 'Fantasy'),
('OL2G', 'Science Fiction');

INSERT INTO books (id, title, authors, genres, publish_date, isbn, number_of_pages) VALUES
('OL1B', 'Harry Potter and the Sorcerers Stone', ARRAY['OL1A'], ARRAY['OL1G'], '1997-06-26', 9780747532699, 320),
('OL2B', 'A Game of Thrones', ARRAY['OL2A'], ARRAY['OL2G'], '1996-08-06', 9780553103540, 694);
