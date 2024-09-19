package com.librare.library;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.MensagemDto;
import com.librare.common.utils.LogUtil;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private RecentViewsService recentViewsService;

    public BookEntity getBookById(String id) {
        try {
            LogUtil.logMessage("Buscando livro com ID: " + id);
            var book = bookRepository.findById(id);
            if (book.isEmpty()) {
                LogUtil.logMessage("Livro não encontrado com ID: " + id);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Livro não encontrado pelo id informado."));
            }
            LogUtil.logObject("Livro encontrado: ", true, book.get());
            recentViewsService.addRecentBook(book.get().getTitle());
            return book.get();
        } catch (ApiException ex) {
            LogUtil.logStackTrace("Erro ao buscar livro com ID: " + id, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching book id: " + ex.getMessage()));
        }
    }

    @Cacheable(value = "authors", key = "#authorName")
    public List<AuthorResponse> searchAuthorByName(String authorName) {
        try {
            LogUtil.logMessage("Buscando autor com nome: " + authorName);
            List<AuthorEntity> authors = authorRepository.findByNameContainingIgnoreCase(authorName);

            if (authors.isEmpty()) {
                LogUtil.logMessage("Nenhum autor encontrado com o nome: " + authorName);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum autor encontrado com o nome " + authorName));
            }

            LogUtil.logObject("Autores encontrados: ", true, authors);

            return authors.stream().map(author -> {

                AuthorResponse.AuthorDetails details = new AuthorResponse.AuthorDetails(author.getName(), author.getBooks().stream()
                        .map(this::mapToBookDetails)
                        .collect(Collectors.toList()));
                AuthorResponse response = new AuthorResponse(details);
                recentViewsService.addRecentAuthor(response.getAuthor().getName());
                return response;
            }).collect(Collectors.toList());

        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar autor com nome: " + authorName, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching author: " + ex.getMessage()));
        }
    }

    @Cacheable(value = "genres", key = "#genre")
    public List<GenreResponse> searchBooksByGenre(String genre) {
        try {
            LogUtil.logMessage("Buscando gênero com nome: " + genre);
            List<GenreEntity> genres = genreRepository.findByNameContainingIgnoreCase(genre);

            if (genres.isEmpty()) {
                LogUtil.logMessage("Nenhum gênero encontrado com o nome: " + genre);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum gênero encontrado com o nome " + genre));
            }

            LogUtil.logObject("Gêneros encontrados: ", true, genres);

            return genres.stream().map(genreEntity -> {
                GenreResponse response = new GenreResponse(genreEntity.getName(), genreEntity.getBooks().stream()
                        .map(this::mapToBookDetailsWithAuthor)
                        .collect(Collectors.toList()));
                recentViewsService.addRecentGenre(response.getGenre());
                return response;
            }).collect(Collectors.toList());

        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar gênero com nome: " + genre, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching genre: " + ex.getMessage()));
        }
    }


    public List<BookResponse> getAllBooks() {
        try {
            LogUtil.logMessage("Buscando todos os livros");
            List<BookEntity> books = bookRepository.findAll();
            if (books.isEmpty()) {
                LogUtil.logMessage("Nenhum livro encontrado");
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum livro disponível."));
            }

            LogUtil.logObject("Livros encontrados: ", true, books);

            return books.stream()
                    .map(this::mapToBookResponse)
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar todos os livros", true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching all books: " + ex.getMessage()));
        }
    }

    public BookResponse mapToBookResponse(BookEntity book) {
        String genres = book.getGenres().stream()
                .map(GenreEntity::getName)
                .collect(Collectors.joining(", "));

        return new BookResponse(
                book.getTitle(),
                book.getAuthors().isEmpty() ? null : book.getAuthors().get(0).getName(),
                genres,
                book.getPublishDate(),
                book.getIsbn(),
                book.getNumberOfPages()
        );
    }

    public AuthorResponse.BookDetails mapToBookDetails(BookEntity book) {
        return new AuthorResponse.BookDetails(
                book.getTitle(),
                book.getPublishDate(),
                book.getIsbn(),
                book.getNumberOfPages()
        );
    }

    public GenreResponse.BookDetails mapToBookDetailsWithAuthor(BookEntity book) {
        return new GenreResponse.BookDetails(
                book.getTitle(),
                book.getAuthors().stream().map(AuthorEntity::getName).collect(Collectors.joining(", ")),
                book.getPublishDate(),
                book.getIsbn(),
                book.getNumberOfPages()
        );
    }
}
