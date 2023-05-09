package cucumber.steps;

import dataobjects.Recipe;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import pageobjects.pages.RecipePage;
import pageobjects.pages.RecipeTouchEnablerPage;

public class RecipeTouchEnablerStepsDefinition {

    private final RecipePage recipePage;
    private final Recipe recipe;
    private final RecipeTouchEnablerPage recipeTouch;

    public RecipeTouchEnablerStepsDefinition(RecipePage recipePage, Recipe recipe, RecipeTouchEnablerPage recipeTouch) {
        this.recipePage = recipePage;
        this.recipe = recipe;
        this.recipeTouch = recipeTouch;
    }

    @And("I add {int} action steps")
    public void addActionSteps(int stepNo) {
        int n = 1;
        while(n<=stepNo) {
            recipeTouch.buttonClick("Add Step");
            recipePage.addActionStep(n);
            n++;
        }
        recipe.setOrgStepCount(recipePage.actionsStepsCount());
    }

    @And("I set a step wait time to {string}")
    public void addWaitTime(String time) {
        String[] value = time.split(" ");
        recipeTouch.buttonClick("Step Wait Time");
        recipePage.setDefaultStepWaitTime(value[0], value[1]);
    }

    @And("I Save the recipe")
    public void saveRecipe() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipeTouch.buttonClick("Save");
        recipePage.saveRecipeNewAndExisting(this.recipe.getRecipeName());
    }

    @And("I paste the copied step after step {int}")
    public void pasteCopiedStep(int stepNo) {
        String stepNumber = Integer.toString(stepNo);
        recipePage.selectStep(stepNumber);
        recipeTouch.buttonClick("Paste Step After");
    }

    @And("I paste the cut step before the step {int}")
    public void pasteStepBefore(int stepNo) {
        String stepNumber = Integer.toString(stepNo);
        recipePage.selectStep(stepNumber);
        recipeTouch.buttonClick("Paste Step Before");
    }

    @And("I should see cut step is pasted")
    public void cutPasteStep(){
        recipePage.verifyRecipeActionStepCount(recipe.getStepCountBeforeCutStep());
        recipePage.verifyStepActionValue(recipe.getStepActionValue());
    }

    @And("I add a criteria to step {string}")
    public void addCriteria(String stepNo){
        recipePage.selectStep(stepNo);
        recipeTouch.buttonClick("Add Criteria");
        recipePage.addCriteriaCondition();
    }

    @And("I delete step {string}")
    public void deleteStep(String stepNo){
        recipePage.selectStep(stepNo);
        recipeTouch.buttonClick("Delete Step");
    }

    @And("I Copy step number {string}")
    public void copyStep(String stepNo){
        recipePage.selectStep(stepNo);
        recipeTouch.buttonClick("Copy Step");
    }

    @And("I Cut Step number {string}")
    public void cutStep(String stepNo){
        recipePage.selectStep(stepNo);
        recipe.setStepActionValue(recipePage.getActionValue());
        recipe.setStepCountBeforeCutStep(recipePage.actionsStepsCount());
        recipeTouch.buttonClick("Cut Step");
    }

    @And("step {string} should be identical to the step {string}")
    public void verifyCopyPasteStep(String copyStepNo, String pasteStepNo) {
        recipePage.compareTwoSteps(copyStepNo, pasteStepNo);
    }

    @And("I should be notified that {string}")
    public void checkNotification(String text) {
        recipePage.recipeNotification(text);
    }

    @Then("I should see step is deleted")
    public void stepDeleted(){
        recipePage.actionStepDeletion(recipe.getOrgStepCount());
    }

    @And("I add Phase using action step {string}")
    public void addPhase(String stepNo){
        this.recipe.setPhaseActionSteps((stepNo));
        recipePage.selectStep(stepNo);
        recipeTouch.buttonClick("Add Phase");
        recipePage.phaseCreationNotification();
        addPhaseAndVerifySuccessText();
    }

    @And("I add phase successfully")
    public void addPhaseAndVerifySuccessText() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhaseAndVerifySuccessMessage(this.recipe.getPhaseName());
        recipePage.verifyPhaseName(this.recipe.getPhaseName());
    }

    @And("I {string}")
    public void copyPhase(String actionButton) {
        recipePage.selectPhase(this.recipe.getPhaseName());
        this.recipe.setPhaseCount(recipePage.phaseCountUsingName(this.recipe.getPhaseName()));
        recipeTouch.buttonClick(actionButton);
    }

    @And("I paste phase {string}")
    public void pastePhase(String actionButton){
        recipePage.selectPhase(this.recipe.getPhaseName());
        recipeTouch.buttonClick(actionButton);
        this.recipe.setPhaseCountCopyPaste(recipePage.phaseCount());
    }

    @And("I verify phase is pasted {string}")
    public void phasePasted(String action){
        recipePage.verifyPhaseName(this.recipe.getPhaseName()+"_2");
        recipePage.verifyPhaseCountAfterPasteAction(this.recipe.getPhaseCountCopyPaste(),this.recipe.getPhaseName()+"_2");
    }

    @And("I verify steps are added in phase")
    public void verifyPhaseSteps(){
        recipePage.verifyPhaseSteps(this.recipe.getPhaseActionSteps());
    }
    
}