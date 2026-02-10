package de.stoll.nicolas.bgraph.person.application.port.in.get;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetPersonQuery {

    int page;

    int size;

    @NotNull
    String firstName;

    @NotNull
    String lastName;
}
