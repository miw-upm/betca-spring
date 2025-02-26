package es.upm.miw.betca_jpa.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity: se mapea en tabla independiente
//Se indica que no se mapea en tabla, sino que se realiza en la tabla de la clases hija
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity extends AbstractRootEntity {
    private String description;

    public AbstractEntity(String nick, String description) {
        super(nick);
        this.description = description;
    }



}
