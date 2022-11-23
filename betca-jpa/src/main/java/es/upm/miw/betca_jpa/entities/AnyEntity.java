package es.upm.miw.betca_jpa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class AnyEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String value;

    public AnyEntity(String value) {
        this.value = value;
    }
}
