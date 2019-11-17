package com.claudiosignorini.genealogy.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventList {

    private List<Event> events;

    public Event getBirth() {
        return events
                .stream()
                .filter(event -> event.getType() == EventType.BIRTH)
                .findFirst()
                .orElse(null);
    }

}
