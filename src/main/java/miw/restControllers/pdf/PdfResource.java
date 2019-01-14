package miw.restControllers.pdf;

import miw.restControllers.exceptions.PdfException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PdfResource.PDFS)
public class PdfResource {

    public static final String PDFS = "/pdfs";

    @GetMapping(produces = {"application/pdf", "application/json"})
    public byte[] state(@RequestParam String title, @RequestParam String paragraph) throws PdfException {
        return new PdfBuilder()
                .paragraphEmphasized(title)
                .line()
                .paragraph(paragraph)
                .build();
    }
}
