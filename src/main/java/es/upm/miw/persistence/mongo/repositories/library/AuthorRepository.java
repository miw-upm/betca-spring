package es.upm.miw.persistence.mongo.repositories.library;

import es.upm.miw.persistence.mongo.documents.library.Author;
import es.upm.miw.persistence.mongo.documents.library.ContactDto;
import es.upm.miw.persistence.mongo.documents.library.NameDto;
import es.upm.miw.persistence.mongo.documents.library.Style;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByStyle(Style style);

    List<NameDto> findNameByStyle(Style style);

    @Query(value = "{'name':?0}", fields = "{'_id':0,'contact':1}")
    List<ContactDto> findContactByName(String name);

    @Query(value = "{'name':?0}", fields = "{'_id':0,'contact.email':1}")
    List<String> findContactEmailJsonByName(String name);

    @Query(value = "{'name':?0}", fields = "{'_id':0,'contact.email':1}")
    List<Author> findContactEmailByName(String name);

}
