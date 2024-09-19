package com.librare.library;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.MensagemDto;
import com.librare.common.utils.LogUtil;
import com.librare.library.author.AuthorDto;
import com.librare.library.author.AuthorEntity;
import com.librare.library.author.AuthorRepository;
import com.librare.library.book.BookDto;
import com.librare.library.book.BookEntity;
import com.librare.library.book.BookRepository;
import com.librare.library.genre.GenreDto;
import com.librare.library.genre.GenreEntity;
import com.librare.library.genre.GenreRepository;
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

    public BookDto getBookById(String id) {
        try {
            LogUtil.logMessage("Buscando livro com ID: " + id);
            var book = bookRepository.findById(id);
            if (book.isEmpty()) {
                LogUtil.logMessage("Livro não encontrado com ID: " + id);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Livro não encontrado pelo id informado."));
            }
            LogUtil.logObject("Livro encontrado: ", true, book.get());
            return mapToBookDto(book.get());
        } catch (ApiException ex) {
            LogUtil.logStackTrace("Erro ao buscar livro com ID: " + id, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching book id: " + ex.getMessage()));
        }
    }

    @Cacheable(value = "authors", key = "#authorName")
    public List<AuthorDto> searchAuthorByName(String authorName) {
        try {
            LogUtil.logMessage("Buscando autor com nome: " + authorName);
            List<AuthorEntity> authorEntities = authorRepository.findByNameContainingIgnoreCase(authorName);

            if (authorEntities.isEmpty()) {
                LogUtil.logMessage("Nenhum autor encontrado com o nome: " + authorName);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum autor encontrato com o nome " + authorName));
            }

            List<AuthorDto> authorDtos = authorEntities.stream()
                    .map(this::mapToAuthorDto)
                    .collect(Collectors.toList());

            LogUtil.logObject("Autores encontrados: ", true, authorDtos);
            return authorDtos;
        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar autor com nome: " + authorName, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching author: " + ex.getMessage()));
        }
    }

    @Cacheable(value = "genres", key = "#genre")
    public List<GenreDto> searchBooksByGenre(String genre) {
        try {
            LogUtil.logMessage("Buscando gênero com nome: " + genre);
            List<GenreEntity> genreEntities = genreRepository.findByNameContainingIgnoreCase(genre);

            if (genreEntities.isEmpty()) {
                LogUtil.logMessage("Nenhum gênero encontrado com o nome: " + genre);
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum gênero encontrado com o nome " + genre));
            }

            List<GenreDto> genreDtos = genreEntities.stream()
                    .map(this::mapToGenreDto)
                    .collect(Collectors.toList());

            LogUtil.logObject("Gêneros encontrados: ", true, genreDtos);
            return genreDtos;
        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar gênero com nome: " + genre, true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching genre: " + ex.getMessage()));
        }
    }


    public List<BookDto> getAllBooks() {
        try {
            LogUtil.logMessage("Buscando todos os livros");
            List<BookEntity> books = bookRepository.findAll();
            if (books.isEmpty()) {
                LogUtil.logMessage("Nenhum livro encontrado");
                throw new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Nenhum livro disponível."));
            }

            List<BookDto> bookDtos = books.stream().map(this::mapToBookDto).collect(Collectors.toList());
            LogUtil.logObject("Livros encontrados: ", true, bookDtos);
            return bookDtos;
        } catch (Exception ex) {
            LogUtil.logStackTrace("Erro ao buscar todos os livros", true, ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, new MensagemDto("500", "Error fetching all books: " + ex.getMessage()));
        }
    }

    private AuthorDto mapToAuthorDto(AuthorEntity author) {
        List<BookDto> books = bookRepository.findByAuthorsContaining(author).stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());

        return new AuthorDto(author.getId(), author.getName(), books);
    }

    private GenreDto mapToGenreDto(GenreEntity genre) {
        List<BookDto> books = bookRepository.findByGenresContaining(genre.getName()).stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());

        return new GenreDto(genre.getId(), genre.getName(), books);
    }

    private BookDto mapToBookDto(BookEntity book) {
        return new BookDto(book.getId(), book.getTitle(), mapToAuthorDtos(book.getAuthors()), mapToGenreDtos(book.getGenres()), book.getPublishDate(), book.getCoverUrl());
    }

    private List<AuthorDto> mapToAuthorDtos(List<AuthorEntity> authors) {
        return authors.stream().map(this::mapToAuthorDto).collect(Collectors.toList());
    }

    private List<GenreDto> mapToGenreDtos(List<GenreEntity> genres) {
        return genres.stream().map(this::mapToGenreDto).collect(Collectors.toList());
    }
}
