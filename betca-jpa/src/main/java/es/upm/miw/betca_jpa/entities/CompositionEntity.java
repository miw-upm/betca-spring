package es.upm.miw.betca_jpa.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompositionEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String nick;

    @Embedded
    private EmbeddableEntity embeddableEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Singular("anyEntity")
    private List<AnyEntity> anyEntityList;

    @Lob
    private AnyClass[] anyClassArray;

}
