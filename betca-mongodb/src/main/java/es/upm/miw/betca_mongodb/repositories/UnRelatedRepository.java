package es.upm.miw.betca_mongodb.repositories;


import es.upm.miw.betca_mongodb.documents.Query1Dto;
import es.upm.miw.betca_mongodb.documents.Query2Dto;
import es.upm.miw.betca_mongodb.documents.UnRelatedDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface UnRelatedRepository extends MongoRepository< UnRelatedDocument, String > {

    UnRelatedDocument findByNickIgnoreCase(String nick);

    List< UnRelatedDocument > findFirst3ByNickStartingWith(String prefix);

    List< UnRelatedDocument > findByNickOrLargeOrderByLongerDesc(String nick, String large);

    List< UnRelatedDocument > findByIntegerGreaterThan(int integer, Pageable pageable);

    @Transactional
    int deleteByNick(String nick);

    // @Query("{'nick':?0}") // Query NOT necessary
    Optional< UnRelatedDocument > findByNick(String nick);

    // FIELDS: _id: incluido por defecto, 1: solo los especificados, 0: todos excepto el especificado
    @Query(value = "{'nick':?0}", fields = "{'bornDate':1,'large':1}")
    Query1Dto findIdAndBornDateAndLargeByNick(String nick);

    @Query(value = "{'nick':?0}", fields = "{'_id':0,'bornDate':1}")
    Query2Dto findBornDateByNick(String nick);

    // @Query("{$and:[{'nick':?0},{'large':?1}]}") // Query NOT necessary
    UnRelatedDocument findByNickAndLarge(String nick, String large);

    // @Query("{nick:{$in:?0} }") // Query NOT necessary
    List< UnRelatedDocument > findByNickIn(Collection nicks);

    //$options: 'i': Case insensitivity // allow NULL: all elements
    @Query("?#{ [0] == null ? { $where : 'true'} : { nick : {$regex:[0], $options: 'i'} } }")
    List< UnRelatedDocument > findByNickLikeIgnoreCaseNullSafe(String nick);

    @Query("{$and:[" // allow NULL: all elements
            + "?#{ [0] == null ? {_id : {$ne:null}} : { nick : {$regex:[0], $options: 'i'} } },"
            + "?#{ [1] == null ? { $where : 'true'} : { large : {$regex:[1], $options: 'i'} } }"
            + "] }")
    List< UnRelatedDocument > findByNickLikeAndLargeLikeNullSafe(String nick, String large);

}
