package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.documents.AggregationDocument;
import es.upm.miw.betca_mongodb.documents.AnyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AggregationRepository extends MongoRepository< AggregationDocument, String > {
    List< AggregationDocument > findByAnyDocumentListContaining(AnyDocument anyDocument);
}
