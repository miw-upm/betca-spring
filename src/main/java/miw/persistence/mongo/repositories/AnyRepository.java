package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.AnyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnyRepository extends MongoRepository<AnyDocument, String> {

    AnyDocument findFirstByValue(String value);
}
