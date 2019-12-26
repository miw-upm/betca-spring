package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class LibraryIT {

    @Autowired
    private RepositoriesLibraryService repositoriesLibraryService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private StyleRepository styleRepository;

    @BeforeEach
    public void populate() {
        repositoriesLibraryService.seedDb();
    }

    @Test
    public void testPopulate() {
        assertTrue(3 == bookRepository.count());
    }

    @Test
    public void testFindByStyle() {
        assertEquals(2, authorRepository.findByStyle(styleRepository.findByNameIgnoreCase("Infantil")).size());
    }

    @Test
    public void testFindNameByStyleName() {
        assertEquals(2, authorRepository.findNameByStyle(styleRepository.findByNameIgnoreCase("Infantil")).size());
    }


    @Test
    public void testFindContactByName() {
        System.out.println("===>>>" + authorRepository.findContactByName("Ana"));
    }

    @Test
    public void testFindContactEmailJsonByName() {
        System.out.println("===>>>" + authorRepository.findContactEmailJsonByName("Ana"));
    }

    @Test
    public void testFindContactEmailByName() {
        System.out.println("===>>>" + authorRepository.findContactEmailByName("Ana").stream().map(
                author -> author.getContact().getEmail()
        ).collect(Collectors.toList()));
    }


    @AfterEach
    public void deleteAll() {
        repositoriesLibraryService.deleteDb();
    }

}
