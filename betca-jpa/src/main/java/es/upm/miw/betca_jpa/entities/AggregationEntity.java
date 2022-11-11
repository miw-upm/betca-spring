package es.upm.miw.betca_jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AggregationEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String nick;
    @ManyToOne //@JoinColumn
    private AnotherEntity anotherEntity;
    @ManyToMany(fetch = FetchType.EAGER) //@JoinColumn
    private List<AnotherEntity> anotherEntityList;
}
