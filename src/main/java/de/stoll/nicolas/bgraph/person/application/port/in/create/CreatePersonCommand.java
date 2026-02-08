package de.stoll.nicolas.bgraph.person.application.port.in.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreatePersonCommand {

    @NotBlank(message = "First name must not be empty")
    String firstName;

    @NotBlank(message = "Last name must not be empty")
    String lastName;

}
