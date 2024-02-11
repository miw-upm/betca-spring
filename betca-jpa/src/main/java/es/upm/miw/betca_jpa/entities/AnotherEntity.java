package es.upm.miw.betca_jpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class AnotherEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public AnotherEntity(String name) {
        this.name = name;
    }
}
