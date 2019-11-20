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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${genealogy.root}")
    private String root;

    private final PersonImporter personImporter;
    private final ParentsImporter parentsImporter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Root folder: {}", root);

        File[] folders =
                new File(root)
                        .listFiles(File::isDirectory);

        if (folders == null || folders.length == 0) {
            throw new BootstrapException("Empty root folder.");
        }

        List<Person> people = Arrays
                .stream(folders)
                .map(file -> new File(file, "person.json"))
                .filter(File::exists)
                .map(this::readPerson)
                .collect(Collectors.toList());

        people.forEach(personImporter::importPerson);
        people.forEach(parentsImporter::importParents);
    }

    private Person readPerson(File personFile) {
        try {
            Person person = objectMapper.readValue(personFile, Person.class);
            person.setKey(personFile.getParentFile().getName());
            return person;
        } catch (IOException e) {
            throw new BootstrapException("Invalid JSON File: " + personFile.getAbsolutePath() + " => " + e.getMessage(), e);
        }
    }

}
