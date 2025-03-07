package es.upm.api.miw.betca.jpa.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class OtherConcreteEntity extends AbstractEntity {
    private String surname;

    public OtherConcreteEntity(String nick, String description, String surname) {
        super(nick, description);
        this.surname = surname;
    }


}
