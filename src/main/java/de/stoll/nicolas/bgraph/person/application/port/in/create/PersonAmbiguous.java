package de.stoll.nicolas.bgraph.person.application.port.in.create;

import de.stoll.nicolas.bgraph.person.application.domain.model.PersonCandidate;

import java.util.List;

public record PersonAmbiguous(List<PersonCandidate> candidates) implements CreatePersonResult {
}
