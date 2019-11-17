package com.claudiosignorini.genealogy.service;

import com.claudiosignorini.genealogy.model.Event;
import com.claudiosignorini.genealogy.model.EventList;
import com.claudiosignorini.genealogy.model.EventType;
import com.claudiosignorini.genealogy.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Person loadPerson(String id) throws IOException {
        File folder = new File("C:\\Users\\othel\\Google Drive\\Albero genealogico\\Parenti", id);
        File file = new File(folder, "persona.json");
        return objectMapper.readValue(file, Person.class);
    }

    public EventList buildEventList(Person person) {
        List<Event> events = new ArrayList<>();
        Optional
                .of(person.getBirth())
                .map(this::completeBirth)
                .ifPresent(events::add);
        return EventList.builder().events(events).build();
    }

    private Event completeBirth(Event birth) {
        birth.setType(EventType.BIRTH);
        birth.setAge(0);
        return birth;
    }

}
