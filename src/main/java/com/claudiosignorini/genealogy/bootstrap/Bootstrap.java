package com.claudiosignorini.genealogy.bootstrap;

import com.claudiosignorini.genealogy.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final ObjectMapper objectMapper = buildObjectMapper();

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Value("${genealogy.root}")
    private String root;

    private final PersonImporter personImporter;
    private final ParentsImporter parentsImporter;
    private final EventsImporter eventsImporter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Root folder: {}", root);

        List<Person> people = list(new File(root), File::isDirectory)
                .flatMap(folder -> list(folder, f -> f.getName().endsWith(".json")))
                .map(this::readPerson)
                .collect(Collectors.toList());

        people.forEach(personImporter::importPerson);
        people.forEach(parentsImporter::importParents);
        people.forEach(eventsImporter::importEvents);
    }

    private static Stream<File> list(File parent, FileFilter filter) {
        File[] files = parent.listFiles(filter);
        if (files == null) {
            files = new File[0];
        }
        return Arrays.stream(files);
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
