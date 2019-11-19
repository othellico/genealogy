package com.claudiosignorini.genealogy.controller;

import com.claudiosignorini.genealogy.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("{key}")
    public String getPerson(@PathVariable String key, Model model) {
        log.info("person key: {}", key);
        personService
                .getPerson(key)
                .ifPresent(person -> {
                    log.info("person: {}", person);
                    model.addAttribute("person", person);
                });
        return "person";
    }

}
