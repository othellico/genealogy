package com.claudiosignorini.genealogy.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Person {

    private Long id;
    private String key;
    private String firstName;
    private String lastName;
    private Event birth;
    private Person father;
    private Person mother;

}
