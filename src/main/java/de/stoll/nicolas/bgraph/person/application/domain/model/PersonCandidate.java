package de.stoll.nicolas.bgraph.person.application.domain.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonCandidate(
        String externalId,
        String label,
        String url,
        String firstName,
        String lastName,
        LocalDate birthDate
) {
}
