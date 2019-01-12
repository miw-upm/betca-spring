package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.OneAndManyToManyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OneAndManyToManyRepository extends MongoRepository<OneAndManyToManyDocument, String> {
}
