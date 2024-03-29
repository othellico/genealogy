package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    private Long id;

    private Person person;

    private Date date;
    private Place place;

    private EventType type;
    private int age;

    public String getDescription() {
        switch(type) {
            case BIRTH:
                return "Nascita";
            case WEDDING:
                return "Matrimonio";
            case DEATH:
                return "Decesso";
            default:
                return "";
        }
    }

}
