package com.librare.library;

import com.librare.common.exception.ApiException;
import com.librare.library.author.AuthorEntity;
import com.librare.library.author.AuthorRepository;
import com.librare.library.author.AuthorResponse;
import com.librare.library.book.BookEntity;
import com.librare.library.book.BookRepository;
import com.librare.library.book.BookResponse;
import com.librare.library.genre.GenreEntity;
import com.librare.library.genre.GenreRepository;
import com.librare.library.genre.GenreResponse;
import com.librare.library.recent.RecentViewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private RecentViewsService recentViewsService;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookByIdSuccess() {
        BookEntity book = new BookEntity();
        book.setId("1");
        book.setTitle("Test Book");

        when(bookRepository.findById("1")).thenReturn(Optional.of(book));

        BookEntity result = libraryService.getBookById("1");

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        verify(recentViewsService).addRecentBook("Test Book");
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById("1")).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> libraryService.getBookById("1"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals("500", exception.getCodigo());
    }

    @Test
    void testSearchAuthorByNameSuccess() {
        AuthorEntity author = new AuthorEntity();
        author.setName("Test Author");
        author.setBooks(new ArrayList<>()); // Garante que a lista de livros não seja nula

        when(authorRepository.findByNameContainingIgnoreCase("Test Author"))
                .thenReturn(Collections.singletonList(author));

        List<AuthorResponse> result = libraryService.searchAuthorByName("Test Author");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Author", result.get(0).getAuthor().getName());
        verify(recentViewsService).addRecentAuthor("Test Author");
    }


    @Test
    void testSearchAuthorByNameNotFound() {
        when(authorRepository.findByNameContainingIgnoreCase("Unknown Author")).thenReturn(Collections.emptyList());

        ApiException exception = assertThrows(ApiException.class, () -> libraryService.searchAuthorByName("Unknown Author"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals("500", exception.getCodigo());
    }

    @Test
    void testSearchBooksByGenreSuccess() {
        GenreEntity genre = new GenreEntity();
        genre.setName("Test Genre");
        genre.setBooks(new ArrayList<>());

        when(genreRepository.findByNameContainingIgnoreCase("Test Genre"))
                .thenReturn(Collections.singletonList(genre));

        List<GenreResponse> result = libraryService.searchBooksByGenre("Test Genre");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Genre", result.get(0).getGenre());
        verify(recentViewsService).addRecentGenre("Test Genre");
    }

    @Test
    void testSearchBooksByGenreNotFound() {
        when(genreRepository.findByNameContainingIgnoreCase("Unknown Genre")).thenReturn(Collections.emptyList());

        ApiException exception = assertThrows(ApiException.class, () -> libraryService.searchBooksByGenre("Unknown Genre"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals("500", exception.getCodigo());
    }

    @Test
    void testGetAllBooksSuccess() {

        AuthorEntity author = new AuthorEntity();
        author.setName("Autor Teste");

        GenreEntity genre = new GenreEntity();
        genre.setName("Ficção");

        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        book.setAuthors(Collections.singletonList(author));
        book.setGenres(Collections.singletonList(genre));
        book.setPublishDate("2024-01-01");
        book.setIsbn("1234567890");
        book.setNumberOfPages(300);

        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<BookResponse> result = libraryService.getAllBooks();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Book", result.get(0).getTitle());
        assertEquals("Autor Teste", result.get(0).getAuthor());
        assertEquals("Ficção", result.get(0).getGenre());
        assertEquals("2024-01-01", result.get(0).getPublishDate());
        assertEquals("1234567890", result.get(0).getIsbn());
        assertEquals(300, result.get(0).getNumberOfPages());
    }


    @Test
    void testGetAllBooksNotFound() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        ApiException exception = assertThrows(ApiException.class, () -> libraryService.getAllBooks());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals("500", exception.getCodigo());
    }

    @Test
    void testMapToBookResponse() {
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setPublishDate("2024-01-01");
        book.setNumberOfPages(300);

        GenreEntity genre = new GenreEntity();
        genre.setName("Test Genre");
        book.setGenres(Collections.singletonList(genre));

        AuthorEntity author = new AuthorEntity();
        author.setName("Test Author");
        book.setAuthors(Collections.singletonList(author));

        BookResponse response = libraryService.mapToBookResponse(book);

        assertNotNull(response);
        assertEquals("Test Book", response.getTitle());
        assertEquals("Test Author", response.getAuthor());
        assertEquals("Test Genre", response.getGenre());
        assertEquals("1234567890", response.getIsbn());
        assertEquals("2024-01-01", response.getPublishDate());
        assertEquals(300, response.getNumberOfPages());
    }

    @Test
    void testMapToBookDetails() {
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setPublishDate("2024-01-01");
        book.setNumberOfPages(300);

        AuthorResponse.BookDetails details = libraryService.mapToBookDetails(book);

        assertNotNull(details);
        assertEquals("Test Book", details.getTitle());
        assertEquals("1234567890", details.getIsbn());
        assertEquals("2024-01-01", details.getPublishDate());
        assertEquals(300, details.getNumberOfPages());
    }

    @Test
    void testMapToBookDetailsWithAuthor() {
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setPublishDate("2024-01-01");
        book.setNumberOfPages(300);

        AuthorEntity author = new AuthorEntity();
        author.setName("Test Author");
        book.setAuthors(Collections.singletonList(author));

        GenreResponse.BookDetails details = libraryService.mapToBookDetailsWithAuthor(book);

        assertNotNull(details);
        assertEquals("Test Book", details.getTitle());
        assertEquals("Test Author", details.getAuthor());
        assertEquals("1234567890", details.getIsbn());
        assertEquals("2024-01-01", details.getPublishDate());
        assertEquals(300, details.getNumberOfPages());
    }
}
