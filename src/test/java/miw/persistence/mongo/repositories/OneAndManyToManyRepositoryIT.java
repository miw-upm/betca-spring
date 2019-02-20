package miw.persistence.mongo.repositories;

import miw.TestConfig;
import miw.persistence.mongo.documents.AnyDocument;
import miw.persistence.mongo.documents.OneAndManyToManyDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<AnyDocument> anyDocumentList = Arrays.asList(
                new AnyDocument("any"),
                new AnyDocument("any2"),
                new AnyDocument("any3")
        );
        this.anyRepository.saveAll(anyDocumentList);
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick",
                Arrays.asList(anyDocumentList.get(0), anyDocumentList.get(1))
        ));
        this.oneToManyRepository.save(new OneAndManyToManyDocument("nick2",
                Arrays.asList(anyDocumentList.get(0), anyDocumentList.get(2))
        ));
    }

    @Test
    public void testCount() {
        assertTrue(oneToManyRepository.count() > 0);
    }

    @Test
    public void testFindByAnyDocumentListContaining() {
        AnyDocument any = anyRepository.findFirstByValue("any");
        AnyDocument any2 = anyRepository.findFirstByValue("any2");
        assertEquals(2, oneToManyRepository.findByAnyDocumentListContaining(any).size());
        assertEquals(1, oneToManyRepository.findByAnyDocumentListContaining(any2).size());
    }


}
