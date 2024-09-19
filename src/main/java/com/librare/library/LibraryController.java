package com.librare.library;

import com.librare.common.exception.ApiException;
import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import com.librare.library.recent.RecentViewsDto;
import com.librare.library.recent.RecentViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private RecentViewsService recentViewsService;

    /**
     * Busca um livro por ID.
     *
     * @param id O identificador do livro.
     * @return BookDto com os detalhes do livro.
     * @throws ApiException se o livro não for encontrado.
     */
    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable String id) {
        return libraryService.getBookById(id);
    }

    /**
     * Retorna a lista de todos os livros disponíveis.
     *
     * @return Lista de BookDto contendo os detalhes de todos os livros.
     */
    @GetMapping("/books/all")
    public List<BookDto> getAllBooks() {
        return libraryService.getAllBooks();
    }

    /**
     * Busca autores pelo nome.
     *
     * @param name O nome ou parte do nome do autor.
     * @return Lista de AuthorDto contendo os detalhes dos autores encontrados e seus livros.
     * @throws ApiException se nenhum autor for encontrado.
     */
    @Cacheable(value = "authors", key = "#name")
    @GetMapping("/authors/{name}")
    public List<AuthorDto> getAuthorByName(@PathVariable String name) {
        return libraryService.searchAuthorByName(name);
    }

    /**
     * Busca livros por gênero.
     *
     * @param genre O nome ou parte do nome do gênero.
     * @return Lista de GenreDto contendo os detalhes dos gêneros e seus livros.
     * @throws ApiException se nenhum gênero for encontrado.
     */
    @Cacheable(value = "genres", key = "#genre")
    @GetMapping("/genres/{name}")
    public List<GenreDto> getBooksByGenre(@PathVariable String genre) {
        return libraryService.searchBooksByGenre(genre);
    }

    /**
     * Retorna a lista de itens recentemente visualizados, incluindo livros, autores e gêneros.
     *
     * @return RecentViewsDto contendo listas de livros, autores e gêneros recentemente visualizados.
     */
    @GetMapping("/recent")
    public RecentViewsDto getRecentViews() {
        List<String> recentBooks = recentViewsService.getRecentBooks();
        List<String> recentAuthors = recentViewsService.getRecentAuthors();
        List<String> recentGenres = recentViewsService.getRecentGenres();

        RecentViewsDto recentViewsDto = new RecentViewsDto();
        recentViewsDto.setBooks(recentBooks);
        recentViewsDto.setAuthors(recentAuthors);
        recentViewsDto.setGenres(recentGenres);

        return recentViewsDto;
    }
}