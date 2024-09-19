-- Criação da tabela, se ainda não existir
CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn BIGINT,
    pages INTEGER,
    publish_year INTEGER
);

-- Inserir 50 livros fictícios
DO $$
DECLARE
    i INTEGER;
BEGIN
    FOR i IN 1..50 LOOP
        INSERT INTO book (title, author, isbn, pages, publish_year)
        VALUES (
            'Book Title ' || i,
            'Author ' || i,
            1000000000000 + i,
            (RANDOM() * 500)::INTEGER + 50,  -- Número aleatório de páginas entre 50 e 550
            2000 + (RANDOM() * 24)::INTEGER
        );
    END LOOP;
END $$;