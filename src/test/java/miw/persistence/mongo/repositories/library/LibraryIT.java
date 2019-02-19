package miw.persistence.mongo.repositories.library;

import miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
        //assertEquals(2, authorRepository.findNameByStyleName("Infantil").size());
        //assertArrayEquals(new String[]{"Jesús", "Cris"}, authorRepository.findNameByStyleName("Infantil").toArray());
    }

    @Test
    public void testFindDistinctNameByAnyBook() {
        //assertEquals(3, authorRepository.findDistinctNameByAnyBook().size());
        //assertArrayEquals(new String[]{"Jesús", "Cris", "Ana"}, authorRepository.findDistinctNameByAnyBook().toArray());
    }

    @Test
    public void testFindNameByThemeName() {
        //assertEquals(2, authorRepository.findNameByThemeName("Suspense").size());
        //assertArrayEquals(new String[]{"Cris", "Ana"}, authorRepository.findNameByThemeName("Suspense").toArray());
    }

    @AfterEach
    public void deleteAll() {
        repositoriesLibraryService.deleteDb();
    }

}
