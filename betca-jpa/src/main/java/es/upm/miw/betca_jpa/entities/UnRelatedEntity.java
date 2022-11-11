package es.upm.miw.betca_jpa.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity//(name = "un_related_entity")
public class UnRelatedEntity {
    public static final String TRANSIENT = "no persistent";
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String nick;
    @Enumerated(EnumType.STRING) //@Column(length = 20)
    private Gender gender;
    //@Temporal(TemporalType.TIMESTAMP) //Or TemporalType.DATE: only year, month & day)
    private LocalDateTime bornDate;
    @ElementCollection(fetch = FetchType.EAGER) // @CollectionTable(name = "un_related_entity_tags")
    @Singular // builder
    private List<String> tags;
    @Transient
    private String noPersistent;
}
