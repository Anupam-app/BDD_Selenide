package dataobjects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Recipe {

    String recipeName;
    String phaseName;
    String recipeNode;
    String runId;
    String productId;
    String batchId;
    String beforeComments;
    String afterComments;
    RecipeAction recipeAction;
    String recipeImportedName;
    String runId2;
    String productId2;
    String batchId2;
    String runId3;
    String startDate;
    String endDate;
    String machineName;
   
}

