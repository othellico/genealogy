package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Slf4j
public class Date {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private Long id;

    private LocalDate exact;
    private LocalDate rangeMin;
    private LocalDate rangeMax;

    public String getValue() {
        if (exact != null) {
            return exact.format(DATE_FORMATTER);
        } else if (rangeMin != null) {
            if (rangeMax != null) {
                return rangeMin.format(DATE_FORMATTER) + " - " + rangeMax.format(DATE_FORMATTER);
            } else {
                return rangeMin.format(DATE_FORMATTER) + " - ?";
            }
        } else if (rangeMax != null) {
            return "? - " + rangeMax.format(DATE_FORMATTER);
        }
        return "?";
    }

}
