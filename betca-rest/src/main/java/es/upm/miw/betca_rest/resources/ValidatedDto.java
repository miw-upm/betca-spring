package es.upm.miw.betca_rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.math.BigDecimal;
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
    @PositiveBigDecimal
    private BigDecimal price;

}
