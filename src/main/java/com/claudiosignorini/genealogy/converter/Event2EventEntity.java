package com.claudiosignorini.genealogy.converter;

import com.claudiosignorini.genealogy.entity.EventEntity;
import com.claudiosignorini.genealogy.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Event2EventEntity implements Converter<Event, EventEntity> {

    private final Date2DateEntity date2DateEntity;
    private final Place2PlaceEntity place2PlaceEntity;

    @Override
    public EventEntity convert(Event event) {
        if (event == null) return null;
        return EventEntity
                .builder()
                .id(event.getId())
                .date(date2DateEntity.convert(event.getDate()))
                .place(place2PlaceEntity.convert(event.getPlace()))
                .age(event.getAge())
                .type(event.getType())
                .build();
    }

}
