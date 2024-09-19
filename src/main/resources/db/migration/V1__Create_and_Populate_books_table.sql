-- Criação das tabelas
CREATE TABLE authors (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE genres (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE books (
    id VARCHAR PRIMARY KEY,
    title VARCHAR NOT NULL,
    authors TEXT[],
    genres TEXT[],
    publish_date DATE
);

-- Índice author
CREATE INDEX idx_authors_name ON authors (name);

-- Índice genrer
CREATE INDEX idx_genres_name ON genres (name);

-- Inserção de dados
INSERT INTO authors (id, name) VALUES
('OL1A', 'J.K. Rowling'),
('OL2A', 'George R.R. Martin');

INSERT INTO genres (id, name) VALUES
('OL1G', 'Fantasy'),
('OL2G', 'Science Fiction');

INSERT INTO books (id, title, authors, genres, publish_date) VALUES
('OL1B', 'Harry Potter and the Sorcerers Stone', ARRAY['OL1A'], ARRAY['OL1G'], '1997-06-26'),
('OL2B', 'A Game of Thrones', ARRAY['OL2A'], ARRAY['OL1G'], '1996-08-06');
