package com.claudiosignorini.genealogy.service;

import com.claudiosignorini.genealogy.converter.Person2PersonEntity;
import com.claudiosignorini.genealogy.converter.PersonEntity2Person;
import com.claudiosignorini.genealogy.entity.PersonEntity;
import com.claudiosignorini.genealogy.model.Person;
import com.claudiosignorini.genealogy.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final Person2PersonEntity person2PersonEntity;
    private final PersonEntity2Person personEntity2Person;
    private final PersonRepository personRepository;

    public Person save(Person person) {
        PersonEntity inputEntity = person2PersonEntity.convert(person);
        PersonEntity savedEntity = personRepository.save(inputEntity);
        return personEntity2Person.convert(savedEntity);
    }

    @Override
    public Optional<Person> getPerson(String key) {
        return personRepository
                .findByKey(key)
                .map(personEntity2Person::convert);
    }

}
