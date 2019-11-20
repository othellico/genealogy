package com.claudiosignorini.genealogy.service;

import com.claudiosignorini.genealogy.model.Person;

import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    Optional<Person> findByKey(String key);

    Person getOrCreatePerson(Person person);
}
