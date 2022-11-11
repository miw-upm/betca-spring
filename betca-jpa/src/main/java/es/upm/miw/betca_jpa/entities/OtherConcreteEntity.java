package es.upm.miw.betca_jpa.entities;

import javax.persistence.Entity;

@Entity
public class OtherConcreteEntity extends AbstractEntity {
    private String surname;

    public OtherConcreteEntity() {
    }

    public OtherConcreteEntity(String nick, String description, String surname) {
        super(nick, description);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

}
