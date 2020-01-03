package es.upm.miw.reactor;

import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import es.upm.miw.persistence.mongo.repositories.UnRelatedReactRepository;
import es.upm.miw.persistence.mongo.repositories.UnRelatedRepository;
import es.upm.miw.restControllers.exceptions.ConflictException;
import es.upm.miw.restControllers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Controller
public class UnRelatedController {

    private UnRelatedReactRepository unRelatedReactRepository;

    private UnRelatedRepository unRelatedRepository;

    @Autowired
    public UnRelatedController(UnRelatedReactRepository unRelatedReactRepository, UnRelatedRepository unRelatedRepository) {
        this.unRelatedReactRepository = unRelatedReactRepository;
        this.unRelatedRepository = unRelatedRepository;
    }

    public LocalDateTime findBornDateByNickAssured(String nick) {
        return this.unRelatedRepository.findByNick(nick)
                .orElseThrow(() -> new NotFoundException("…"))
                .getBornDate();
    }

    public Mono<LocalDateTime> findBornDateByNickAssuredReact(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(UnRelatedDocument::getBornDate);
    }

    public LocalDateTime findBornDateByNickAssuredReactERRORChainBreak(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(UnRelatedDocument::getBornDate).block();
    }

    public void notExistsByNickAssured(String nick) {
        if (this.unRelatedRepository.findByNick(nick).isPresent()) {
            throw new ConflictException("…");
        }
    }

    public Mono<Void> notExistsByNickAssuredReact(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .handle((document, sink) -> sink.error(new ConflictException("…")));
    }

    public void findByNickAssuredAndUpdateLarge(String nick, String large) {
        UnRelatedDocument entity = this.unRelatedRepository.findByNick(nick)
                .orElseThrow(() -> new NotFoundException("…"));
        entity.setLarge(large);
        this.unRelatedRepository.save(entity);
    }

    public Mono<Void> findByNickAssuredAndUpdateLargeReact(String nick, String large) {
        Mono<UnRelatedDocument> unRelated = this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(document -> {
                    document.setLarge(large);
                    return document;
                });
        return this.unRelatedReactRepository.saveAll(unRelated).then();
    }

    public Mono<Void> updateDocumentsReact() {
        Flux<UnRelatedDocument> documents = unRelatedReactRepository.findAll().map(
                document -> {
                    document.setLarge("new");
                    return document;
                });
        return unRelatedReactRepository.saveAll(documents).then();
    }

}
