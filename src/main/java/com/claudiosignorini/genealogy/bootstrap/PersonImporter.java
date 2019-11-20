package com.claudiosignorini.genealogy.bootstrap;

import com.claudiosignorini.genealogy.model.Person;
import com.claudiosignorini.genealogy.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class PersonImporter {

    private final PersonService personService;

    void importPerson(Person person) {
        // Annullo padre e madre perchè li imposto nel secondo ciclo
        Person father = person.getFather();
        Person mother = person.getMother();
        person.setFather(null);
        person.setMother(null);
        personService.save(person);
        person.setFather(father);
        person.setMother(mother);
        log.info("  person.json imported.");
    }


}
