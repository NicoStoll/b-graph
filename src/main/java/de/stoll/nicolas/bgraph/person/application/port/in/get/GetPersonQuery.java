package de.stoll.nicolas.bgraph.person.application.port.in.get;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetPersonQuery {

    int page;

    int size;
}
