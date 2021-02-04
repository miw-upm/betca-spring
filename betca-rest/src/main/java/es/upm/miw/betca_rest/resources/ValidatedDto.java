package es.upm.miw.betca_rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedDto {

    @NotNull
    @Min(10)
    private Integer id;
    @NotBlank // Not null, not empty and not only blank
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Gender gender;
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bornDate;

}
