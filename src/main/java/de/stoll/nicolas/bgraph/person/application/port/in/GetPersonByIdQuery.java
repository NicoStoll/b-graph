package de.stoll.nicolas.bgraph.person.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetPersonByIdQuery {

    String id;
}
