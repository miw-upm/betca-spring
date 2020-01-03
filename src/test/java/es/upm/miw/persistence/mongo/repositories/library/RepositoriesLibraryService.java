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
        Theme[] themes = {
                new Theme("Acción"),
                new Theme("Suspense"),
                new Theme("Drama")};
        themeRepository.saveAll(Arrays.asList(themes));

        Style[] styles = {
                new Style("Infantil", "Lectura sencilla"),
                new Style("Especializado", "Expertos de la temática")};
        styleRepository.saveAll(Arrays.asList(styles));

        Author[] authors = {
                new Author("Jesús", "Ber", new Contact("j@gmail.com", 666666661), styles[0]),
                new Author("Cris", "Ber", new Contact("c@gmail.com", 666666662), styles[0]),
                new Author("Ana", "Ber", new Contact("a@gmail.com", 666666663), styles[1]),
                new Author("Ana", "Reb", new Contact("a2@gmail.com", 666666663), styles[1])};
        authorRepository.saveAll(Arrays.asList(authors));

        Book[] books = {
                new Book("isbn1", "La naranja", Arrays.asList(themes[0], themes[2]), Arrays.asList(authors[0])),
                new Book("isbn2", "El limón", Arrays.asList(themes[1]), Arrays.asList(authors[1], authors[2])),
                new Book("isbn3", "El pepino", Arrays.asList(themes[0]), Arrays.asList(authors[2]))};
        bookRepository.saveAll(Arrays.asList(books));
    }

    public void deleteDb() {
        bookRepository.deleteAll();
        themeRepository.deleteAll();
        authorRepository.deleteAll();
        styleRepository.deleteAll();
    }
}
