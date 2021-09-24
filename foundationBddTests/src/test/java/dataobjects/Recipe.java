package dataobjects;

import lombok.Getter;
import lombok.Setter;

public class Recipe {
    @Setter
    @Getter
    String recipeName;

    @Setter
    @Getter
    String phaseName;
}
