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

    @Setter
    @Getter
    String recipeNode;

    @Setter
    @Getter
    String runId;

    @Setter
    @Getter
    String productId;

    @Setter
    @Getter
    String batchId;

    @Setter
    @Getter
    String beforeComments;

    @Setter
    @Getter
    String afterComments;
    
    @Setter
    @Getter
    RecipeAction recipeAction;

    @Setter
    @Getter
    String recipeImportedName;
}
