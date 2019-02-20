package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToManyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OneAndManyToManyRepository extends MongoRepository<OneAndManyToManyDocument, String> {

    List<OneAndManyToManyDocument> findByAnyDocumentListContaining(AnyDocument anyDocument);
}
