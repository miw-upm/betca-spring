package miw.persistence.jpa.entities.library;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Style {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    private String description;

    public Style() {
    }

    public Style(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Style [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Style)) {
            return false;
        }
        return id == ((Style) obj).id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

}
