package miw.persistence.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneAndManyToOneDocument;

public interface OneAndManyToOneRepository extends MongoRepository<OneAndManyToOneDocument, String> {
    
    
    // @Query("{'anyDocument':{'$ref':'anyDocument','$id':?0 } }") //not necessary
    List<OneAndManyToOneRepository> findByAnyDocumentId(String id);

}
