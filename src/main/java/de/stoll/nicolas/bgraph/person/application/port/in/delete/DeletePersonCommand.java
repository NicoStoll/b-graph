package de.stoll.nicolas.bgraph.person.application.port.in.delete;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePersonCommand {

    private String id;
}
