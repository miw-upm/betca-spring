package miw.restControllers.pdf;

import miw.restControllers.ApiTestConfig;
import miw.restControllers.RestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

@ApiTestConfig
class PdfResourceIT {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    void testPdfs() {
        String json = new RestBuilder<String>(port).clazz(String.class).path(PdfResource.PDFS)
                .param("title", "BETCA: Spring 5. PDF").param("paragraph", "paragraph")
                .get().build();

    }

}
