package model;

import annotation.Lookup;
import lombok.Data;

@Data
public class RowStructure {

    @Lookup(regex = "(?<category>[A-Z][a-z]+( [A-Z][a-z]+)?)")
    private String category;

    @Lookup(regex = "(?<budget>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String budget;

    @Lookup(regex = "(?<actual>[0-9]+,[0-9]+\\h[A-Z]{3})")
    private String actual;

}
