package de.stoll.nicolas.bgraph.person.application.port.in.get;

import de.stoll.nicolas.bgraph.person.application.port.in.ValidPersonId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetPersonByIdQuery {

    @ValidPersonId
    String id;
}
