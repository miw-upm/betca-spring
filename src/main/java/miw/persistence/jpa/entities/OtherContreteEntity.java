package miw.persistence.jpa.entities;

import javax.persistence.Entity;

@Entity
public class OtherContreteEntity extends AbstractEntity {

    private String surname;

    public OtherContreteEntity() {
    }

    public OtherContreteEntity(String nick, String description, String surname) {
        super(nick, description);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

}
