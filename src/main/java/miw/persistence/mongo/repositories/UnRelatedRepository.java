package miw.persistence.mongo.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import miw.persistence.mongo.documents.Query1Dto;
import miw.persistence.mongo.documents.Query2Dto;
import miw.persistence.mongo.documents.UnRelatedDocument;

public interface UnRelatedRepository extends MongoRepository<UnRelatedDocument, String> {
    // Consulta: por Nombre de Método
    UnRelatedDocument findByNickIgnoreCase(String nick);

    List<UnRelatedDocument> findFirst3ByNickStartingWith(String prefix);

    List<UnRelatedDocument> findByNickOrLargeOrderByLogerDesc(String nick, String large);

    List<UnRelatedDocument> findByIntegerGreaterThan(int integer, Pageable pageable);

    List<UnRelatedDocument> findByNickIn(Collection<String> values);

    @Transactional
    int deleteByNick(String nick);

    // Consultas por Query

    @Query("{'nick':?0}") // not necessary
    UnRelatedDocument findByNick(String nick);

    // FIELDS: _id: incluido por defecto, 1: solo los especificados, 0: todos excepto el especificado
    @Query(value = "{'nick':?0}", fields = "{'bornDate':1,'large':1}")
    Query1Dto findIdAndBornDateAndLargeByNick(String nick);

    @Query(value = "{'nick':?0}", fields = "{'_id':0,'bornDate':1}")
    Query2Dto findBornDateByNick(String nick);

    @Query("{$and:[{'nick':?0},{'large':?1}]}") // not necessary
    UnRelatedDocument findByNickAndLarge(String nick, String large);

}
