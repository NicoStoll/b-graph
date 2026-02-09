package de.stoll.nicolas.bgraph.person.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class Person {

    private String id;

    private String firstName;

    private String lastName;
}
