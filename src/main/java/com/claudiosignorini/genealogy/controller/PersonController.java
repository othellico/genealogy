package com.claudiosignorini.genealogy.controller;

import com.claudiosignorini.genealogy.model.EventList;
import com.claudiosignorini.genealogy.model.Person;
import com.claudiosignorini.genealogy.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("{id}")
    public String getPerson(@PathVariable String id, Model model) throws IOException {
        log.info("person id: {}", id);
        Person person = personService.loadPerson(id);
        log.info("person: {}", person);
        model.addAttribute("person", person);
        EventList eventList = personService.buildEventList(person);
        model.addAttribute("eventList", eventList);
        return "person";
    }

}
