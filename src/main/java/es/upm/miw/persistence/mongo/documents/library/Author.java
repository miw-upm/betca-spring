package es.upm.miw.persistence.mongo.documents.library;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author {

    @Id
    private String id;

    private String name;

    private String surname;

    private Contact contact;

    @DBRef
    private Style style;

    public Author() {
    }

    public Author(String name, String surname, Contact contact, Style style) {
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.style = style;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Author) obj).id));
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contact=" + contact +
                ", style=" + style +
                '}';
    }
}
