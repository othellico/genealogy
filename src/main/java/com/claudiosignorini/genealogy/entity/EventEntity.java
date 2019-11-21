package com.claudiosignorini.genealogy.entity;

import com.claudiosignorini.genealogy.model.EventType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private DateEntity date;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    // TODO: gestire l'entity!!!
    private EventType type;

    private int age;

}
