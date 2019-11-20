package com.claudiosignorini.genealogy.bootstrap;

import com.claudiosignorini.genealogy.model.Person;
import com.claudiosignorini.genealogy.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${genealogy.root}")
    private String root;

    private final PersonService personService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Root folder: {}", root);

        File[] folders =
                new File(root)
                .listFiles(File::isDirectory);

        if (folders == null || folders.length == 0) {
            log.error("Empty root folder.");
        }
        else {
            Arrays
                    .stream(folders)
                    .forEach(this::importPerson);
            Arrays
                    .stream(folders)
                    .forEach(this::importParents);
        }
    }

    private void importParents(File file) {
        try {
            log.info("Importing {} parents:", file.getName());
            File personJsonFile = new File(file, "person.json");
            if (personJsonFile.exists()) {
                Person readPerson = objectMapper.readValue(personJsonFile, Person.class);
                if (readPerson.getFather() != null || readPerson.getMother() != null) {
                    Person dbPerson = personService.getPerson(file.getName()).get();

                    if (readPerson.getFather() != null) {
                        Person father = personService.getPerson(readPerson.getFather().getKey());
                    }

                    log.info("  parents imported.");
                }
            } else {
                log.info("  person.json file not found.");
            }
        }
        catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
    }

    private void importPerson(File file) {
        try {
            log.info("Importing {}:", file.getName());
            File personJsonFile = new File(file, "person.json");
            if (personJsonFile.exists()) {
                Person person = objectMapper.readValue(personJsonFile, Person.class);
                person.setKey(file.getName());
                // Annullo padre e madre perchè li imposto nel secondo ciclo
                person.setFather(null);
                person.setMother(null);
                personService.save(person);
                log.info("  person.json imported.");
            } else {
                log.info("  person.json file not found.");
            }
        }
        catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
    }

}
