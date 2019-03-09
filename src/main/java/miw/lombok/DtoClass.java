package miw.lombok;

import lombok.*;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DtoClass {
    private Integer id;
    private String name;
    private String surName;
    private String email;
}
