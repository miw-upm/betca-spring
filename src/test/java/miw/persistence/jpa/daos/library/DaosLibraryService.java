package miw.persistence.jpa.daos.library;

import miw.persistence.jpa.entities.library.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DaosLibraryService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private StyleDao styleDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ThemeDao themeDao;

    public void seedDb() {
        Theme[] themeArray = {new Theme("Acción"), new Theme("Suspense"), new Theme("Drama")};
        themeDao.saveAll(Arrays.asList(themeArray));

        Style[] styleArray = {new Style("Infantil", "Lectura sencilla"), new Style("Especializado", "Expertos de la temática")};
        styleDao.saveAll(Arrays.asList(styleArray));

        Author[] authorArray = {new Author("Jesús", "Ber", new Contact("j@gmail.com", 666666661), styleArray[0]),
                new Author("Cris", "Ber", new Contact("c@gmail.com", 666666662), styleArray[0]),
                new Author("Ana", "Ber", new Contact("a@gmail.com", 666666663), styleArray[1]),
                new Author("Ana", "Reb", new Contact("a2@gmail.com", 666666663), styleArray[1])};
        authorDao.saveAll(Arrays.asList(authorArray));

        Book[] bookArray = {new Book("isbn1", "El calabacín", Arrays.asList(themeArray[0], themeArray[2]), Arrays.asList(authorArray[0])),
                new Book("isbn2", "La mazorca", Arrays.asList(themeArray[1]), Arrays.asList(authorArray[1], authorArray[2])),
                new Book("isbn3", "El pepino", Arrays.asList(themeArray[0]), Arrays.asList(authorArray[2]))};
        bookDao.saveAll(Arrays.asList(bookArray));
    }

    public void deleteDb() {
        bookDao.deleteAll();
        themeDao.deleteAll();
        authorDao.deleteAll();
        styleDao.deleteAll();
    }
}
