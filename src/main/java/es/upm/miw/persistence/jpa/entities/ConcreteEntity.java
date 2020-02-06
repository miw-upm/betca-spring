package es.upm.miw.persistence.jpa.entities;

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

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || ((obj != null) && (getClass().equals(obj.getClass())) && (this.getId().equals(((ConcreteEntity) obj).getId())));
    }


}
