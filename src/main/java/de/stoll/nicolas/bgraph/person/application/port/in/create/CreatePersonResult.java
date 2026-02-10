package de.stoll.nicolas.bgraph.person.application.port.in.create;

public sealed interface CreatePersonResult permits PersonCreated, PersonAlreadyExists, PersonAmbiguous {

}
