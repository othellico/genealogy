package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {

    private String building;
    private String municipality;
    private String region;
    private String state;

    public String getValue() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        first = append(builder, building, first);
        first = append(builder, municipality, first);
        first = append(builder, region, first);
        first = append(builder, state, first);
        return builder.toString();
    }

    private static boolean append(StringBuilder builder, String item, boolean first) {
        if (item != null) {
            if (first) {
                first = false;
            }
            else {
                builder.append(", ");
            }
            builder.append(item);
        }
        return first;
    }

}
