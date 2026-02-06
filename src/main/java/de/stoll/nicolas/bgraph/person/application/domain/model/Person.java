package de.stoll.nicolas.bgraph.person.application.domain.model;

import lombok.Builder;

@Builder
public record Person(
        String id,
        String firstname,
        String lastname) {
}
