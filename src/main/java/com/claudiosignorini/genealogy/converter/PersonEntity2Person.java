package com.claudiosignorini.genealogy.converter;

import com.claudiosignorini.genealogy.entity.PersonEntity;
import com.claudiosignorini.genealogy.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonEntity2Person implements Converter<PersonEntity, Person> {

    @Override
    public Person convert(PersonEntity personEntity) {
        return Person
                .builder()
                .key(personEntity.getKey())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .build();
    }

}
