package es.upm.miw.persistence.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnotherEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String value;

    public AnotherEntity() {
        //Empty for framework
    }

    public AnotherEntity(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((AnotherEntity) obj).id));
    }

    @Override
    public String toString() {
        return "AnotherEntity [id=" + id + ", value=" + value + "]";
    }

}
