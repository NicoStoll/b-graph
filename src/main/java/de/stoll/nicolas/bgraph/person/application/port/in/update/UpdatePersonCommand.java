package de.stoll.nicolas.bgraph.person.application.port.in.update;

import de.stoll.nicolas.bgraph.person.application.domain.model.Person;
import de.stoll.nicolas.bgraph.person.application.port.in.ValidPersonId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePersonCommand {

    @ValidPersonId
    String id;

    @NotBlank(message = "First name must not be empty")
    String firstName;

    @NotBlank(message = "Last name must not be empty")
    String lastName;
}
