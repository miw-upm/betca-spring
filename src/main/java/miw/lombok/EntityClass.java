package miw.lombok;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityClass {
    @EqualsAndHashCode.Include
    //@Setter(AccessLevel.NONE) for exceptions
    private Integer id;
    private String name;
    private String surName;
    private String email;
}
