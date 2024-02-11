package es.upm.miw.betca_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdvancedEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String nick;
    @Lob
    private String large;
}
