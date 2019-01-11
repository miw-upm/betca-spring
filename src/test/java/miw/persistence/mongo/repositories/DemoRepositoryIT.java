package miw.persistence.mongo.repositories;

import miw.TestConfig;
import miw.persistence.mongo.documents.Demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class DemoRepositoryIT {

    @Autowired
    private DemoRepository demoRepository;

    @BeforeEach
    public void populate() {
        this.demoRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            this.demoRepository.save(new Demo("id" + i, "desc" + i, "ref" + i));
        }
    }

    @Test
    public void testReadAll() {
        System.out.println("All: " + demoRepository.findAll());
    }

    @Test
    public void testFindFieldsById() {
        System.out.println("Id: " + demoRepository.findFieldsById("id2"));
    }

}
