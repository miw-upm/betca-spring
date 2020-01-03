package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.persistence.jpa.entities.library.Style;
import es.upm.miw.persistence.mongo.documents.library.Author;
import es.upm.miw.persistence.mongo.documents.library.Book;
import es.upm.miw.persistence.mongo.documents.library.EmailDto;
import es.upm.miw.persistence.mongo.documents.library.Theme;
import es.upm.miw.persistence.mongo.repositories.library.AuthorRepository;
import es.upm.miw.persistence.mongo.repositories.library.BookRepository;
import es.upm.miw.persistence.mongo.repositories.library.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private AuthorRepository authorRepository;

    private ThemeRepository themeRepository;

    private BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, ThemeRepository themeRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.themeRepository = themeRepository;
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        Style style = new Style("Uno", "dos");
        Query query5 = new Query();
        query5.addCriteria(Criteria.where("age").is(style));
        System.out.println("query5 - " + query5.toString());
    }

    public List<EmailDto> findContactEmailByAuthorName(String name) {
        return this.authorRepository.findContactEmailByName(name).
                stream().map(contact -> new EmailDto(contact.getContact().getEmail()))
                .collect(Collectors.toList());
    }

    public List<Author> findAuthorByBookByThemeName(String name) {
        List<Theme> themes = this.themeRepository.findByName(name);
        return this.bookRepository.findByThemeListIn(themes)
                .map(Book::getAuthorList).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<String> findAuthorNameByExampleAuthor(Author author) {
        return this.authorRepository.findAll(Example.of(author))
                .stream().map(Author::getName).collect(Collectors.toList());
    }
}
