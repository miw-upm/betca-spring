package es.upm.api.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.api.domain.model.validations.Validations;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @NotNull
    @NotBlank
    @Pattern(regexp = Validations.NINE_DIGITS)
    private String mobile;
    @NotNull
    @NotBlank
    private String firstName;
    private String familyName;
    private String email;
    private String dni;
    private String address;
    private String password;
    private Role role;
    private LocalDateTime registrationDate;
    private Boolean active;

    public void doDefault() {
        if (Objects.isNull(password)) {
            password = UUID.randomUUID().toString();
        }
        if (Objects.isNull(role)) {
            this.role = Role.CUSTOMER;
        }
        if (Objects.isNull(active)) {
            this.active = true;
        }
    }


    public User ofMobileFirstName() {
       return User.builder().mobile(this.getMobile()).firstName(this.getFirstName()).build();
    }
}
