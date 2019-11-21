package com.claudiosignorini.genealogy.service;

import com.claudiosignorini.genealogy.converter.Event2EventEntity;
import com.claudiosignorini.genealogy.entity.EventEntity;
import com.claudiosignorini.genealogy.entity.PersonEntity;
import com.claudiosignorini.genealogy.model.Event;
import com.claudiosignorini.genealogy.repository.EventRepository;
import com.claudiosignorini.genealogy.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final Event2EventEntity event2EventEntity;
    private final EventEntity2Event eventEntity2Event;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        EventEntity eventEntity = event2EventEntity.convert(event);
        Optional<PersonEntity> opt = personRepository.findByKey(event.getPerson().getKey());
        if (opt.isPresent()) {
            eventEntity.setPerson(opt.get());
            EventEntity savedEntity = eventRepository.save(eventEntity);
            return eventEntity2Event.convert(savedEntity);
        } else {
            throw new PersonNotFoundException(event.getPerson());
        }
        return null;
    }

}
