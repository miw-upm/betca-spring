package miw.persistence.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.AnyDocument;

public interface AnyRepository extends MongoRepository<AnyDocument, String> {
    
    List<AnyDocument> findByValue(String value);
}
