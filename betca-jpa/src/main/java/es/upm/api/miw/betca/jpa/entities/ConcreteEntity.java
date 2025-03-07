package es.upm.api.miw.betca.jpa.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConcreteEntity extends AbstractEntity {
    private String name;

}
