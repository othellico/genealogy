package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {

    private String firstName;
    private String lastName;
    private Event birth;

}
