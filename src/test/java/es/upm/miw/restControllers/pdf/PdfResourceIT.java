package es.upm.miw.restControllers.pdf;

import es.upm.miw.restControllers.ApiTestConfig;
import es.upm.miw.restControllers.RestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@ApiTestConfig
class PdfResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    void testPdfs() {
        new RestBuilder<String>(port).clazz(String.class).path(PdfResource.PDFS)
                .param("title", "BETCA: Spring 5. PDF").param("paragraph", "paragraph")
                .get().build();

    }

}
