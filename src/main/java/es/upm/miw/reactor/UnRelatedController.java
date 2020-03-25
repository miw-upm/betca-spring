package es.upm.miw.reactor;

import es.upm.miw.persistence.mongo.documents.Gender;
import es.upm.miw.persistence.mongo.documents.UnRelatedDocument;
import es.upm.miw.persistence.mongo.repositories.UnRelatedReactRepository;
import es.upm.miw.persistence.mongo.repositories.UnRelatedRepository;
import es.upm.miw.rest_controllers.exceptions.ConflictException;
import es.upm.miw.rest_controllers.exceptions.NotFoundException;
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
    public UnRelatedController(UnRelatedReactRepository unRelatedReactRepository,
                               UnRelatedRepository unRelatedRepository) {
        this.unRelatedReactRepository = unRelatedReactRepository;
        this.unRelatedRepository = unRelatedRepository;
    }

    public LocalDateTime findBornDateByNickAssuredSynchronous(String nick) {
        return this.unRelatedRepository.findByNick(nick)
                .orElseThrow(() -> new NotFoundException("…"))
                .getBornDate();
    }

    public Mono<LocalDateTime> findBornDateByNickAssuredAsynchronous(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(UnRelatedDocument::getBornDate);
    }

    public LocalDateTime findBornDateByNickAssuredSynchronousERRORChainBreak(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(UnRelatedDocument::getBornDate).block();
    }

    public void notExistsByNickAssuredSynchronous(String nick) {
        if (this.unRelatedRepository.findByNick(nick).isPresent()) {
            throw new ConflictException("…");
        }
    }

    public Mono<Void> notExistsByNickAssuredAsynchronous(String nick) {
        return this.unRelatedReactRepository.findByNick(nick)
                .handle((document, sink) -> sink.error(new ConflictException("…")));
    }

    public UnRelatedDocument findByNickIsFemaleAssuredSynchronous(String nick) {
        UnRelatedDocument document = this.unRelatedRepository.findByNick(nick)
                .orElseThrow(() -> new NotFoundException("…"));
        if (document.getGender().equals(Gender.MALE)) {
            throw new ConflictException("…");
        }
        return document;
    }

    public Mono<UnRelatedDocument> findByNickIsFemaleAssuredAsynchronous(String nick) {
        Mono<UnRelatedDocument> unRelated = this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")));
        return unRelated.handle((document, sink) -> {
            if (document.getGender().equals(Gender.FEMALE)) {
                sink.next(document);
            } else {
                sink.error(new ConflictException("…"));
            }
        });
    }

    public void findByNickAssuredAndUpdateLargeSynchronous(String nick, String large) {
        UnRelatedDocument document = this.unRelatedRepository.findByNick(nick)
                .orElseThrow(() -> new NotFoundException("…"));
        document.setLarge(large);
        this.unRelatedRepository.save(document);
    }

    public Mono<Void> findByNickAssuredAndUpdateLargeAsynchronous(String nick, String large) {
        Mono<UnRelatedDocument> unRelated = this.unRelatedReactRepository.findByNick(nick)
                .switchIfEmpty(Mono.error(new NotFoundException("…")))
                .map(document -> {
                    document.setLarge(large);
                    return document;
                });
        return this.unRelatedReactRepository.saveAll(unRelated).then();
    }

    public Mono<Void> updateDocumentsAsynchronous() {
        Flux<UnRelatedDocument> documents = this.unRelatedReactRepository.findAll()
                .map(document -> {
                    document.setLarge("new");
                    return document;
                });
        return this.unRelatedReactRepository.saveAll(documents).then();
    }

    public Mono<Void> findByGenderAndIfMaleThenLongerX2ElseLongerX3AndUpdateAll() {
        Flux<UnRelatedDocument> maleDocuments = this.unRelatedReactRepository.findByGender(Gender.MALE)
                .map(document -> {
                    document.setLonger(document.getLonger() * 2);
                    return document;
                });
        Flux<UnRelatedDocument> femaleDocuments = this.unRelatedReactRepository.findByGender(Gender.FEMALE)
                .map(document -> {
                    document.setLonger(document.getLonger() * 3);
                    return document;
                });
        return Mono.when(this.unRelatedReactRepository.saveAll(maleDocuments),
                this.unRelatedReactRepository.saveAll(femaleDocuments));
    }

}
