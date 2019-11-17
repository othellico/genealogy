package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Slf4j
public class Date {

    private String date;

    public String getValue() {
        try {
            if (date != null) {
                java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                return new SimpleDateFormat("dd/MM/yyyy").format(temp);
            }
            return "";
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return "Errore";
        }
    }

}
