package de.stoll.nicolas.bgraph.person.application.port.in.delete;

import de.stoll.nicolas.bgraph.person.application.port.in.ValidPersonId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePersonCommand {

    @ValidPersonId
    private String id;
}
