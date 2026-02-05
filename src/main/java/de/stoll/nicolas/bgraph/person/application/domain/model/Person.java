package de.stoll.nicolas.bgraph.person.application.domain.model;

import lombok.Builder;

@Builder
public record Person(
        long id,
        String firstname,
        String lastname) {
}
