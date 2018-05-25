package miw.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneToManyEmbeddedDocument;

public interface OneToManyEmbeddedRepository extends MongoRepository<OneToManyEmbeddedDocument, String> {
}
