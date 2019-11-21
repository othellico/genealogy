package com.claudiosignorini.genealogy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {

    private Long id;

    private String building;
    private String village;
    private String municipality;
    private String province;
    private String region;
    private String state;

    public String getValue() {
        StringBuilder builder = new StringBuilder();
        boolean first = append(builder, building, true);
        first = append(builder, village, first);
        first = append(builder, municipality, first);
        first = append(builder, province, first);
        first = append(builder, region, first);
        append(builder, state, first);
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
