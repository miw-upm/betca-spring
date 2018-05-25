package miw.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneAndManyToManyDocument;

public interface OneAndManyToManyRepository extends MongoRepository<OneAndManyToManyDocument, String> {
}
