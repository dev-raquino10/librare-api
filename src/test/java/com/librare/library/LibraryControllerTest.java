package com.librare.library;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.MensagemDto;
import com.librare.library.author.AuthorResponse;
import com.librare.library.book.BookEntity;
import com.librare.library.book.BookResponse;
import com.librare.library.genre.GenreResponse;
import com.librare.library.recent.RecentViewsDto;
import com.librare.library.recent.RecentViewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryControllerTest {

    @Mock
    private LibraryService libraryService;

    @Mock
    private RecentViewsService recentViewsService;

    @InjectMocks
    private LibraryController libraryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookByIdSuccess() {
        BookEntity mockBook = new BookEntity();
        mockBook.setId("B01");
        mockBook.setTitle("Test Book");

        when(libraryService.getBookById("B01")).thenReturn(mockBook);

        BookEntity result = libraryController.getBookById("B01");

        assertNotNull(result);
        assertEquals("B01", result.getId());
        assertEquals("Test Book", result.getTitle());

        verify(libraryService, times(1)).getBookById("B01");
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(libraryService.getBookById("B01")).thenThrow(new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("404", "Book not found")));

        assertThrows(ApiException.class, () -> libraryController.getBookById("B01"));

        verify(libraryService, times(1)).getBookById("B01");
    }

    @Test
    public void testGetAllBooks() {
        List<BookResponse> mockBooks = Arrays.asList(
                new BookResponse("Book 1", "Author 1", "Fantasy", "2020-03-20", "1234567890", 300),
                new BookResponse("Book", "Book 2", "Sci-Fi", "2020-03-20", "1234567891", 350)
        );

        when(libraryService.getAllBooks()).thenReturn(mockBooks);

        List<BookResponse> result = libraryController.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());

        verify(libraryService, times(1)).getAllBooks();
    }

    @Test
    public void testGetAuthorByName() {
        List<AuthorResponse> mockAuthors = Arrays.asList(
                new AuthorResponse(new AuthorResponse.AuthorDetails(
                        "Author 1", List.of(new AuthorResponse.BookDetails("Book 1", "2020", "1234567890", 300))
                )),
                new AuthorResponse(new AuthorResponse.AuthorDetails(
                        "Author 2", List.of(new AuthorResponse.BookDetails("Book 2", "2021", "1234567891", 350))
                ))
        );

        when(libraryService.searchAuthorByName("Author")).thenReturn(mockAuthors);

        List<AuthorResponse> result = libraryController.getAuthorByName("Author");

        assertEquals(2, result.size());
        assertEquals("Author 1", result.get(0).getAuthor().getName());

        verify(libraryService, times(1)).searchAuthorByName("Author");
    }

    @Test
    public void testGetBooksByGenre() {
        List<GenreResponse> mockGenres = Arrays.asList(
                new GenreResponse("Fantasy", List.of(new GenreResponse.BookDetails("Book 1", "Author 1", "2020", "1234567890", 300))),
                new GenreResponse("Sci-Fi", List.of(new GenreResponse.BookDetails("Book 2", "Author 2", "2021", "1234567891", 350)))
        );

        when(libraryService.searchBooksByGenre("Fantasy")).thenReturn(mockGenres);

        List<GenreResponse> result = libraryController.getBooksByGenre("Fantasy");

        assertEquals(2, result.size());
        assertEquals("Fantasy", result.get(0).getGenre());

        verify(libraryService, times(1)).searchBooksByGenre("Fantasy");
    }

    @Test
    public void testGetRecentViews() {
        List<String> mockBooks = Arrays.asList("Book 1", "Book 2");
        List<String> mockAuthors = Arrays.asList("Author 1", "Author 2");
        List<String> mockGenres = Arrays.asList("Fantasy", "Sci-Fi");

        when(recentViewsService.getRecentBooks()).thenReturn(mockBooks);
        when(recentViewsService.getRecentAuthors()).thenReturn(mockAuthors);
        when(recentViewsService.getRecentGenres()).thenReturn(mockGenres);

        RecentViewsDto result = libraryController.getRecentViews();

        assertEquals(2, result.getBooks().size());
        assertEquals("Book 1", result.getBooks().get(0));

        verify(recentViewsService, times(1)).getRecentBooks();
        verify(recentViewsService, times(1)).getRecentAuthors();
        verify(recentViewsService, times(1)).getRecentGenres();
    }
}
