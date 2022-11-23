package es.upm.miw.betca_rest.resources;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(BasicResource.BASIC)
public class BasicResource {
    public static final String BASIC = "/basic";
    public static final String ID_ID = "/{id}";
    public static final String NAME = "/name";
    public static final String SEARCH = "/search";

    @PostMapping
    public Dto create(@RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> create: " + dto);
        return dto;
    }

    @GetMapping(ID_ID)
    public Dto read(@PathVariable(value = "id") int id) {
        return new Dto(id, "daemon", Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
    }

    @PutMapping(ID_ID)
    public Dto update(@PathVariable(value = "id") int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> update: " + id + ", " + dto);
        return dto;
    }

    @PutMapping(ID_ID + NAME)
    public Dto updateName(@PathVariable(value = "id") int id, @RequestBody Dto dto) {
        LogManager.getLogger(this.getClass()).info("===>>> update: " + id + ", " + dto.getName());
        return new Dto(id, dto.getName(), Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN);
    }

    @PatchMapping
    public Stream<Dto> updateNames(@RequestBody List<UpdatingDto> updatingNames) {
        return updatingNames.stream()
                .map(updatingDto -> new Dto(updatingDto.getId(), updatingDto.getName(), Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN));
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable(value = "id") int id) {
        LogManager.getLogger(this.getClass()).info("===>>> delete: " + id);
    }

    @GetMapping(SEARCH)
    public Stream<Dto> findByName(@RequestParam String name) {
        return Stream.of(
                new Dto(1, name, Gender.MALE, LocalDateTime.now(), BigDecimal.TEN),
                new Dto(2, name, Gender.FEMALE, LocalDateTime.now(), BigDecimal.TEN),
                new Dto(3, name, Gender.MALE, LocalDateTime.now(), BigDecimal.TEN)
        );
    }
}
