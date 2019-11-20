package com.claudiosignorini.genealogy.bootstrap;

import com.claudiosignorini.genealogy.model.Person;
import com.claudiosignorini.genealogy.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
class ParentsImporter {

    private final PersonService personService;

    void importParents(Person person) {
        if (person.getFather() != null || person.getMother() != null) {
            Optional<Person> opt = personService.findByKey(person.getKey());
            if (opt.isPresent()) {
                Person dbPerson = opt.get();

                if (person.getFather() != null) {
                    Person dbFatherPerson = personService.getOrCreatePerson(person.getFather());
                    dbPerson.setFather(dbFatherPerson);
                }
                if (person.getMother() != null) {
                    Person dbMotherPerson = personService.getOrCreatePerson(person.getMother());
                    dbPerson.setMother(dbMotherPerson);
                }
                personService.save(dbPerson);
            } else {
                throw new BootstrapException("Person key not found: " + person.getKey());
            }
        }
    }

}
