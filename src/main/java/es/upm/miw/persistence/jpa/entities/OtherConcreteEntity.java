package es.upm.miw.persistence.jpa.entities;

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

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || ((obj != null) && (getClass().equals(obj.getClass())) && (this.getId().equals(((OtherConcreteEntity) obj).getId())));
    }


}
