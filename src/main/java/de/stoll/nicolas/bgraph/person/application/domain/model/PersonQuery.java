package de.stoll.nicolas.bgraph.person.application.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonQuery {

    private int page;

    private int size;
}
