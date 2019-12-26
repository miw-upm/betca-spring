package es.upm.miw.persistence.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnyEntity {

    @Id
    @GeneratedValue
    private int id;

    private String value;

    public AnyEntity() {
    }

    public AnyEntity(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnyEntity [id=" + id + ", value=" + value + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AnyEntity)) {
            return false;
        }
        return id == ((AnyEntity) obj).id;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
