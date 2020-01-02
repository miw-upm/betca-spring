package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.TestConfig;
import es.upm.miw.persistence.mongo.documents.library.*;
import es.upm.miw.persistence.mongo.repositories.LibraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class LibraryIT {

    @Autowired
    private RepositoriesLibraryService repositoriesLibraryService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private StyleRepository styleRepository;

    @BeforeEach
    void populate() {
        repositoriesLibraryService.seedDb();
    }

    @Test
    void testPopulate() {
        assertTrue(bookRepository.count() >= 3);
    }

    @Test
    void testFindByStyleName() {
        assertTrue(authorRepository.findByStyle(styleRepository.findByNameIgnoreCase("Infantil"))
                .stream().map(Author::getName).anyMatch("Jesús"::equals));
    }

    @Test
    void testFindNameByStyleName() {
        assertTrue(authorRepository.findNameByStyle(styleRepository.findByNameIgnoreCase("Infantil"))
                .stream().map(NameDto::getName).anyMatch("Jesús"::equals));
    }

    @Test
    void testFindByContactEmail() {
        assertTrue(authorRepository.findByContactEmail("j@gmail.com")
                .stream().map(Author::getName).anyMatch("Jesús"::equals));
    }

    @Test
    void testFindContactByName() {
        assertTrue(authorRepository.findContactByName("Ana")
                .stream().map(ContactDto::getContact).map(Contact::getEmail).anyMatch("a@gmail.com"::equals));
    }

    @Test
    void testFindContactEmailByName() {
        assertTrue(libraryService.findContactEmailByAuthorName("Ana")
                .stream().map(EmailDto::getEmail).anyMatch("a@gmail.com"::equals));
    }

    @Test
    void testFindAuthorByBookByThemeName() {
        assertTrue(libraryService.findAuthorByBookByThemeName("Acción")
                .stream().map(Author::getName).anyMatch(name -> "Jesús".equals(name) || "Ana".equals(name)));
    }

    @Test
    void testFindAuthorNameByExampleAuthor() {
        Author author = new Author("Ana", "Ber", null, null);
        System.out.println("===>>>" + libraryService.findAuthorNameByExampleAuthor(author));
    }

    @AfterEach
    void deleteAll() {
        repositoriesLibraryService.deleteDb();
    }

}
