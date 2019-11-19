package com.claudiosignorini.genealogy.converter;

import com.claudiosignorini.genealogy.entity.PersonEntity;
import com.claudiosignorini.genealogy.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Person2PersonEntity implements Converter<Person, PersonEntity> {

    @Override
    public PersonEntity convert(Person person) {
        return PersonEntity
                .builder()
                .key(person.getKey())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }

}
