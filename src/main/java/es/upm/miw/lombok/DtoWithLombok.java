package es.upm.miw.lombok;

import lombok.*;

import java.util.List;


@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // only documents
@NoArgsConstructor
@AllArgsConstructor
public class DtoWithLombok {
    @EqualsAndHashCode.Include // only documents
    private String id;
    private String name;
    private String surName;
    private String email;
    @Singular // collections types
    private List<String> tags;
}

