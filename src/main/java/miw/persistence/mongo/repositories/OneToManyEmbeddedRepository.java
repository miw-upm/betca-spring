package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.OneToManyEmbeddedDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OneToManyEmbeddedRepository extends MongoRepository<OneToManyEmbeddedDocument, String> {
}
