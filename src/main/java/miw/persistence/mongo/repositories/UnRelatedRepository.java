package miw.persistence.mongo.repositories;

import miw.persistence.mongo.documents.Query1Dto;
import miw.persistence.mongo.documents.Query2Dto;
import miw.persistence.mongo.documents.UnRelatedDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface UnRelatedRepository extends MongoRepository<UnRelatedDocument, String> {
    // Consulta: por Nombre de Método
    UnRelatedDocument findByNickIgnoreCase(String nick);

    List<UnRelatedDocument> findFirst3ByNickStartingWith(String prefix);

    List<UnRelatedDocument> findByNickOrLargeOrderByLogerDesc(String nick, String large);

    List<UnRelatedDocument> findByIntegerGreaterThan(int integer, Pageable pageable);


    @Transactional
    int deleteByNick(String nick);

    // Consultas por Query

    @Query("{'nick':?0}")
        // NOT necessary
    UnRelatedDocument findByNick(String nick);

    // FIELDS: _id: incluido por defecto, 1: solo los especificados, 0: todos excepto el especificado
    @Query(value = "{'nick':?0}", fields = "{'bornDate':1,'large':1}")
    Query1Dto findIdAndBornDateAndLargeByNick(String nick);

    @Query(value = "{'nick':?0}", fields = "{'_id':0,'bornDate':1}")
    Query2Dto findBornDateByNick(String nick);

    @Query("{$and:[{'nick':?0},{'large':?1}]}")
        // NOT necessary
    UnRelatedDocument findByNickAndLarge(String nick, String large);

    @Query("{nick:{$in:?0} }")
        // NOT necessary
    List<UnRelatedDocument> findByNickIn(Collection nicks);

    @Query("?#{ [0] == null ? { $where : 'true'} : { nick : {$regex:[0], $options: 'i'} } }")
        //$options: 'i': Case insensitivity
    List<UnRelatedDocument> findByNickLikeNullSafe(String nick);  // allow NULL: all elements

    @Query("{$and:["
            + "?#{ [0] == null ? { $where : 'true'} : { nick : {$regex:[0], $options: 'i'} } },"
            + "?#{ [1] == null ? { $where : 'true'} : { large : {$regex:[1], $options: 'i'} } }"
            + "] }")
    List<UnRelatedDocument> findByNickLikeAndLargeLikeNullSafe(String nick, String large);  // allow NULL: all elements
}
