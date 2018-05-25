package miw.persistence.jpa.daos.library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import miw.persistence.jpa.entities.library.Author;
import miw.persistence.jpa.entities.library.Style;

public interface AuthorDao extends JpaRepository<Author, Integer> {

    List<Author> findByStyle(Style style);

    @Query("select author.name from Author author where author.style.name = ?1")
    List<String> findNameByStyleName(String styleName);

    @Query("select distinct author.name from Book book join book.authorList author")
    List<String> findDistinctNameByAnyBook();

    @Query("select author.name from Book book join book.authorList author join book.themeList theme where theme.name = ?1")
    List<String> findNameByThemeName(String themeName);
}
