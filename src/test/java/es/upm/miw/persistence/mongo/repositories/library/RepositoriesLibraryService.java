package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RepositoriesLibraryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public void seedDb() {
        Theme[] themeArray = {new Theme("Acción"), new Theme("Suspense"), new Theme("Drama")};
        themeRepository.saveAll(Arrays.asList(themeArray));

        Style[] styleArray = {new Style("Infantil", "Lectura sencilla"), new Style("Especializado", "Expertos de la temática")};
        styleRepository.saveAll(Arrays.asList(styleArray));

        Author[] authorArray = {new Author("Jesús", "Ber", new Contact("j@gmail.com", 666666661), styleArray[0]),
                new Author("Cris", "Ber", new Contact("c@gmail.com", 666666662), styleArray[0]),
                new Author("Ana", "Ber", new Contact("a@gmail.com", 666666663), styleArray[1]),
                new Author("Ana", "Reb", new Contact("a2@gmail.com", 666666663), styleArray[1])};
        authorRepository.saveAll(Arrays.asList(authorArray));

        Book[] bookArray = {new Book("isbn1", "El calabacín", Arrays.asList(themeArray[0], themeArray[2]), Arrays.asList(authorArray[0])),
                new Book("isbn2", "La mazorca", Arrays.asList(themeArray[1]), Arrays.asList(authorArray[1], authorArray[2])),
                new Book("isbn3", "El pepino", Arrays.asList(themeArray[0]), Arrays.asList(authorArray[2]))};
        bookRepository.saveAll(Arrays.asList(bookArray));
    }

    public void deleteDb() {
        bookRepository.deleteAll();
        themeRepository.deleteAll();
        authorRepository.deleteAll();
        styleRepository.deleteAll();
    }
}
