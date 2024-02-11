package es.upm.miw.betca_jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "any_entity")
public class AnyEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public AnyEntity(String name) {
        this.name = name;
    }
}
