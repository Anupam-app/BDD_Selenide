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
    String startDate;
    String endDate;
    String machineName;
    String status;
    String ManualOperationName;
    String steps;
    String saveAsRecipeName;
    String stepActionValue;
    int orgStepCount;
    int stepCountBeforeCutStep;
    int phaseCount;
}

