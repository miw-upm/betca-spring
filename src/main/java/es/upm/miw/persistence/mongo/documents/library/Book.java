package es.upm.miw.persistence.mongo.documents.library;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Book {


    @Id
    private String id;

    private String isbn;

    private String title;

    @DBRef(lazy = true)
    private List<Theme> themeList;

    @DBRef(lazy = true)
    private List<Author> authorList;

    public Book() {
    }

    public Book(String isbn, String title, List<Theme> themeList, List<Author> authorList) {
        this.isbn = isbn;
        this.title = title;
        this.themeList = themeList;
        this.authorList = authorList;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Theme> getThemeList() {
        return themeList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Book) obj).id));
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", themeList=" + themeList +
                ", authorList=" + authorList +
                '}';
    }
}
