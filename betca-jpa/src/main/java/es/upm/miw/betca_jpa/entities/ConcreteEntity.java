package es.upm.miw.betca_jpa.entities;

import javax.persistence.Entity;

@Entity
public class ConcreteEntity extends AbstractEntity {
    private String name;
    public ConcreteEntity() {
    }
    public ConcreteEntity(String nick, String description, String name) {
        super(nick, description);
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
