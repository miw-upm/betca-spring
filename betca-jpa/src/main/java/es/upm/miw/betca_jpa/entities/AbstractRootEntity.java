package es.upm.miw.betca_jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//se mapea en una tabla, InheritanceType.JOINED: Una tabla por clase
public abstract class AbstractRootEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String nick;

    public AbstractRootEntity(String nick) {
        this.nick = nick;
    }


}
