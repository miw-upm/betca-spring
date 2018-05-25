package miw.persistence.mongo.repositories;

import java.util.ArrayList;
import java.util.List;

import miw.TestConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToManyDocument;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OneAndManyToManyRepositoryIT {

    @Autowired
    private OneAndManyToManyRepository oneToManyRepository;

    @Autowired
    private AnyRepository anyRepository;

    @BeforeEach
    public void seedDb() {
        this.oneToManyRepository.deleteAll();
        this.anyRepository.deleteAll();
        List<AnyDocument> anyDocumentList = new ArrayList<>();
        anyDocumentList.add(new AnyDocument("any"));
        anyDocumentList.add(new AnyDocument("any2"));
        this.anyRepository.saveAll(anyDocumentList);
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick", anyDocumentList));
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick2", anyDocumentList));
    }

    @Test
    public void test() {
        assertTrue(oneToManyRepository.count() > 0);
    }

}
