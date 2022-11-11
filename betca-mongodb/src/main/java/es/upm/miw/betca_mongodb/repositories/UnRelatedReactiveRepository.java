package es.upm.miw.betca_mongodb.repositories;

import es.upm.miw.betca_mongodb.documents.UnRelatedDocument;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface UnRelatedReactiveRepository extends ReactiveSortingRepository<UnRelatedDocument, String> {
    Flux<UnRelatedDocument> findByNick(String nick);
}
