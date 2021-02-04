package es.upm.miw.betca_mongodb.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableDocument {

    private int number;

    private String value;

}
