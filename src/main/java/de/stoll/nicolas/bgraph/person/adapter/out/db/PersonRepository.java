package de.stoll.nicolas.bgraph.person.adapter.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
}
