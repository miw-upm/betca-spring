package es.upm.miw.betca_mongodb.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document //(collection = "un_related")
public class UnRelatedDocument {

    public static final String TRANSIENT = "no persistent";

    @Id
    private String id;

    @Indexed(unique = true) //@Field(value = "kcin")
    private String nick;

    private Gender gender; // String

    // @DateTimeFormat(iso=ISO.DATE) // ISO.DATE_TIME, ISO.TIME
    // ISODate: YYYY-MM-DDThh:mm:ss.sTZD, TZD = time zone designator (Z or +hh:mm or -hh:mm)
    // (1997-07-16T19:20:30.45+01:00) (2018-02-22T22:20Z)
    private LocalDateTime bornDate;

    private String[] strings;

    private String large;

    private Boolean logic;

    private Integer integer;

    private Long longer;

    private Double decimal;

    @Transient
    private String noPersistent;

}
