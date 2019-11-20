package com.claudiosignorini.genealogy.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private PersonEntity father;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private PersonEntity mother;

}
