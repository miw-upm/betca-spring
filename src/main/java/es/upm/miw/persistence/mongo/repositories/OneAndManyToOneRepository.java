package es.upm.miw.persistence.mongo.repositories;

import es.upm.miw.persistence.mongo.documents.OneAndManyToOneDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OneAndManyToOneRepository extends MongoRepository<OneAndManyToOneDocument, String> {


    @Query("{'anyDocument':{'$ref':'anyDocument','$id':?0 } }")
        //not necessary
    List<OneAndManyToOneRepository> findByAnyDocumentId(String id);

}
