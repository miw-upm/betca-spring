package es.upm.miw.betca_jpa.entities;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "composition_entity")
public class CompositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nick;
    @Embedded
    private EmbeddableEntity embeddableEntity;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "anyEntity_id", nullable = false)
    @Singular("anyEntity")
    private List<AnyEntity> anyEntityList;
}
