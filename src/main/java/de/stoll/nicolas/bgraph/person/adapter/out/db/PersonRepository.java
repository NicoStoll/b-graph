package de.stoll.nicolas.bgraph.person.adapter.out.db;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<PersonEntity, String> {
}
