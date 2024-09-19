-- Criação das tabelas
CREATE TABLE IF NOT EXISTS authors (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    birth_date VARCHAR(255),
    death_date VARCHAR(255)
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
    number_of_pages INTEGER
);

-- Criação de índices
CREATE INDEX IF NOT EXISTS idx_authors_name ON authors (name);
CREATE INDEX IF NOT EXISTS idx_genres_name ON genres (name);

-- Inserção de dados de autores
INSERT INTO authors (id, name, birth_date, death_date) VALUES
('A01', 'J.K. Rowling', '1965-07-31', NULL),
('A02', 'George R.R. Martin', '1948-09-20', NULL),
('A03', 'Isaac Asimov', '1920-01-02', '1992-04-06'),
('A04', 'Philip K. Dick', '1928-12-16', '1982-03-02'),
('A05', 'Arthur C. Clarke', '1917-12-16', '2008-03-19'),
('A06', 'Suzanne Collins', '1962-08-10', NULL),
('A07', 'Tolkien', '1892-01-03', '1973-09-02'),
('A08', 'H.G. Wells', '1866-09-21', '1946-08-13'),
('A09', 'Margaret Atwood', '1939-11-18', NULL),
('A10', 'Neil Gaiman', '1960-11-10', NULL),
('A11', 'Stephen King', '1947-09-21', NULL),
('A12', 'Ray Bradbury', '1920-08-22', '2012-06-05'),
('A13', 'Orson Scott Card', '1951-08-24', NULL),
('A14', 'Ursula K. Le Guin', '1929-10-21', '2018-01-22'),
('A15', 'Terry Pratchett', '1948-04-28', '2015-03-12'),
('A16', 'Douglas Adams', '1952-03-11', '2001-05-11'),
('A17', 'Aldous Huxley', '1894-07-26', '1963-11-22'),
('A18', 'Frank Herbert', '1920-10-08', '1986-02-11'),
('A19', 'Mary Shelley', '1797-08-30', '1851-02-01'),
('A20', 'Jules Verne', '1828-02-08', '1905-03-24'),
('A21', 'Brandon Sanderson', '1975-12-19', NULL),
('A22', 'Rick Riordan', '1964-06-05', NULL),
('A23', 'C.S. Lewis', '1898-11-29', '1963-11-22'),
('A25', 'Philip Pullman', '1946-10-19', NULL),
('A27', 'James Dashner', '1972-11-26', NULL),
('A28', 'Michael Crichton', '1942-10-23', '2008-11-04'),
('A29', 'Dan Brown', '1964-06-22', NULL),
('A30', 'Kurt Vonnegut', '1922-11-11', '2007-04-11'),
('A31', 'George Orwell', '1903-06-25', '1950-01-21'),
('A32', 'Roald Dahl', '1916-09-13', '1990-11-23'),
('A33', 'Agatha Christie', '1890-09-15', '1976-01-12'),
('A34', 'Ernest Hemingway', '1899-07-21', '1961-07-02'),
('A35', 'Harper Lee', '1926-04-28', '2016-02-19'),
('A36', 'Mark Twain', '1835-11-30', '1910-04-21'),
('A37', 'F. Scott Fitzgerald', '1896-09-24', '1940-12-21'),
('A38', 'Jane Austen', '1775-12-16', '1817-07-18'),
('A39', 'Leo Tolstoy', '1828-09-09', '1910-11-20'),
('A40', 'John Steinbeck', '1902-02-27', '1968-12-20'),
('A41', 'Victor Hugo', '1802-02-26', '1885-05-22'),
('A42', 'Herman Melville', '1819-08-01', '1891-09-28'),
('A43', 'Charles Dickens', '1812-02-07', '1870-06-09'),
('A44', 'Louisa May Alcott', '1832-11-29', '1888-03-06'),
('A45', 'Emily Bronte', '1818-07-30', '1848-12-19');

-- Inserção de dados de gêneros
INSERT INTO genres (id, name) VALUES
('G01', 'Fantasy'),
('G02', 'Science Fiction'),
('G03', 'Dystopian'),
('G04', 'Adventure'),
('G05', 'Romance'),
('G06', 'Horror'),
('G09', 'Thriller'),
('G10', 'Historical Fiction'),
('G11', 'Detective'),
('G12', 'Psychological Thriller'),
('G13', 'Classic Literature');

-- Inserção de dados de livros
INSERT INTO books (id, title, authors, genres, publish_date, isbn, number_of_pages) VALUES
('B01', 'Harry Potter and the Sorcerers Stone', ARRAY['A01'], ARRAY['G01'], '1997-06-26', 9780747532699, 320),
('B02', 'A Game of Thrones', ARRAY['A02'], ARRAY['G01', 'G04'], '1996-08-06', 9780553103540, 694),
('B03', 'Foundation', ARRAY['A03'], ARRAY['G02'], '1951-06-01', 9780553293357, 255),
('B04', 'Do Androids Dream of Electric Sheep?', ARRAY['A04'], ARRAY['G02'], '1968-01-01', 9780345404473, 210),
('B05', '2001: A Space Odyssey', ARRAY['A05'], ARRAY['G02'], '1968-07-01', 9780451457998, 297),
('B06', 'The Hunger Games', ARRAY['A06'], ARRAY['G03', 'G04'], '2008-09-14', 9780439023481, 374),
('B07', 'The Lord of the Rings', ARRAY['A07'], ARRAY['G01', 'G04'], '1954-07-29', 9780261103252, 1216),
('B08', 'The War of the Worlds', ARRAY['A08'], ARRAY['G02'], '1898-04-01', 9780141441030, 192),
('B09', 'The Handmaids Tale', ARRAY['A09'], ARRAY['G03'], '1985-07-01', 9780385490818, 311),
('B10', 'American Gods', ARRAY['A10'], ARRAY['G01'], '2001-06-19', 9780062572233, 465),
('B11', 'The Shining', ARRAY['A11'], ARRAY['G06'], '1977-01-28', 9780307743657, 659),
('B12', 'Fahrenheit 451', ARRAY['A12'], ARRAY['G02', 'G03'], '1953-10-19', 9781451673319, 256),
('B13', 'Enders Game', ARRAY['A13'], ARRAY['G02'], '1985-01-15', 9780812550702, 324),
('B14', 'The Left Hand of Darkness', ARRAY['A14'], ARRAY['G02'], '1969-03-01', 9780441478125, 304),
('B15', 'Good Omens', ARRAY['A10', 'A15'], ARRAY['G01'], '1990-05-01', 9780060853983, 432),
('B16', 'The Hitchhikers Guide to the Galaxy', ARRAY['A16'], ARRAY['G02', 'G04'], '1979-10-12', 9780345391803, 224),
('B17', 'Brave New World', ARRAY['A17'], ARRAY['G03'], '1932-12-01', 9780060850524, 268),
('B18', 'Dune', ARRAY['A18'], ARRAY['G02'], '1965-08-01', 9780441013593, 688),
('B19', 'Frankenstein', ARRAY['A19'], ARRAY['G06'], '1818-01-01', 9780141439471, 280),
('B20', '20,000 Leagues Under the Sea', ARRAY['A20'], ARRAY['G02', 'G04'], '1870-01-01', 9780553213454, 416),
('B21', 'Mistborn: The Final Empire', ARRAY['A21'], ARRAY['G01'], '2006-07-17', 9780765377135, 672),
('B22', 'The Lightning Thief', ARRAY['A22'], ARRAY['G04'], '2005-06-28', 9780786838653, 400),
('B23', 'The Chronicles of Narnia', ARRAY['A23'], ARRAY['G01'], '1950-10-16', 9780064471190, 768),
('B24', 'I, Robot', ARRAY['A03'], ARRAY['G02'], '1950-12-02', 9780553294385, 272),
('B25', 'The Golden Compass', ARRAY['A25'], ARRAY['G01', 'G04'], '1995-07-01', 9780440418320, 399),
('B26', 'The Maze Runner', ARRAY['A27'], ARRAY['G03'], '2009-10-06', 9780385737951, 384),
('B27', 'Jurassic Park', ARRAY['A28'], ARRAY['G02'], '1990-11-20', 9780345538987, 448),
('B28', 'The Da Vinci Code', ARRAY['A29'], ARRAY['G09'], '2003-03-18', 9780307474278, 689),
('B29', 'Slaughterhouse-Five', ARRAY['A30'], ARRAY['G02'], '1969-03-31', 9780385333849, 275),
('B30', '1984', ARRAY['A31'], ARRAY['G03'], '1949-06-08', 9780451524935, 328),
('B31', 'Charlie and the Chocolate Factory', ARRAY['A32'], ARRAY['G04'], '1964-01-17', 9780142410318, 176),
('B32', 'Murder on the Orient Express', ARRAY['A33'], ARRAY['G11'], '1934-01-01', 9780062693665, 265),
('B33', 'The Old Man and the Sea', ARRAY['A34'], ARRAY['G13'], '1952-09-01', 9780684830490, 128),
('B34', 'To Kill a Mockingbird', ARRAY['A35'], ARRAY['G13'], '1960-07-11', 9780060935467, 281),
('B35', 'The Adventures of Tom Sawyer', ARRAY['A36'], ARRAY['G13'], '1876-01-01', 9780486400778, 274),
('B36', 'The Great Gatsby', ARRAY['A37'], ARRAY['G13'], '1925-04-10', 9780743273565, 180),
('B37', 'Pride and Prejudice', ARRAY['A38'], ARRAY['G05'], '1813-01-28', 9780141439518, 279),
('B38', 'War and Peace', ARRAY['A39'], ARRAY['G13'], '1869-01-01', 9781400079988, 1225),
('B39', 'The Grapes of Wrath', ARRAY['A40'], ARRAY['G13'], '1939-04-14', 9780143039433, 464),
('B40', 'Les Misérables', ARRAY['A41'], ARRAY['G13'], '1862-01-01', 9780140444308, 1463),
('B41', 'Moby-Dick', ARRAY['A42'], ARRAY['G04'], '1851-10-18', 9780142437247, 720),
('B42', 'A Tale of Two Cities', ARRAY['A43'], ARRAY['G13'], '1859-01-01', 9780141439600, 448),
('B43', 'Little Women', ARRAY['A44'], ARRAY['G05'], '1868-01-01', 9780142408766, 759),
('B44', 'Wuthering Heights', ARRAY['A45'], ARRAY['G05'], '1847-12-01', 9780141439556, 416);
