package es.upm.miw.betca_jpa.entities;

import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "any_entity")
public class AnyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;

    public AnyEntity(String value) {
        this.value = value;
    }
}
