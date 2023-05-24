package cucumber.steps;

import dataobjects.Recipe;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import pageobjects.pages.RecipePage;
import pageobjects.pages.RecipeTouchEnablerPage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashSet;

import static com.codeborne.selenide.Selenide.switchTo;


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
        this.recipe.setOrgStepCount(recipePage.actionsStepsCount());
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
        addingPhase(stepNo);
        addPhaseAndVerifySuccessText();
    }

    @And("I add phase successfully")
    public void addPhaseAndVerifySuccessText() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhaseAndVerifySuccessMessage(this.recipe.getPhaseName());
        recipePage.verifyPhaseName(this.recipe.getPhaseName());
    }

    @And("I Copy Phase")
    public void copyPhase() {
        recipePage.selectPhase(this.recipe.getPhaseName());
        this.recipe.setPhaseCount(recipePage.phaseCountUsingName(this.recipe.getPhaseName()));
        recipeTouch.buttonClick("Copy Phase");
    }

    @And("I Cut Phase {int}")
    public void cutPhase(int phaseNumber) {
        String phaseName = null;
        if(phaseNumber==1){
            phaseName = this.recipe.getPhaseName();
        } else if (phaseNumber== 2){
            phaseName = this.recipe.getSecondPhaseName();
        }
        recipePage.selectPhase(phaseName);
        this.recipe.setPhaseCount(recipePage.phaseCountUsingName(phaseName));
        recipeTouch.buttonClick("Cut Phase");
    }

    @And("I paste phase {string}")
    public void pastePhase(String actionButton){
        recipePage.selectPhase(this.recipe.getPhaseName());
        recipeTouch.buttonClick(actionButton);
        this.recipe.setPhaseCountCopyPaste(recipePage.phaseCount());
    }

    @And("I paste phase {string} phase {int}")
    public void cutPastePhase(String actionButton, int phaseNo){
        String phaseName = null;
        if(phaseNo==1){
            phaseName = this.recipe.getPhaseName();
        } else if (phaseNo== 2){
            phaseName = this.recipe.getSecondPhaseName();
        }
        recipePage.selectPhase(phaseName);
        recipeTouch.buttonClick(actionButton);
        this.recipe.setPhaseCountCopyPaste(recipePage.phaseCount());
    }

    @And("I verify copied phase is pasted")
    public void copyPhasePasted(){
        recipePage.verifyPhaseName(this.recipe.getPhaseName()+"_2");
        recipePage.verifyPhaseCountAfterPasteAction(this.recipe.getPhaseCountCopyPaste(),this.recipe.getPhaseName()+"_2");
    }

    @And("I verify phase is pasted {string} phase")
    public void cutPhasePasted(String action){
        recipePage.verifyPhaseName(this.recipe.getPhaseName());
        recipePage.verifyPhaseName(this.recipe.getSecondPhaseName());
        Set<String> cutPasteAfterList = new HashSet<>();
        cutPasteAfterList.add(this.recipe.getPhaseName());
        cutPasteAfterList.add(this.recipe.getSecondPhaseName());
        List<String> list = new ArrayList<>(cutPasteAfterList);
        list.sort(Collections.reverseOrder());
        Set<String> cutPasteBeforeList= new LinkedHashSet<>(list);
        if(action.equalsIgnoreCase("After")){
            recipePage.phaseListOrder(cutPasteAfterList);
        }else if (action.equalsIgnoreCase("Before")){
            recipePage.phaseListOrder(cutPasteBeforeList);
        }
    }

    @And("I verify steps are added in phase")
    public void verifyPhaseSteps(){
        recipePage.verifyPhaseSteps(this.recipe.getPhaseActionSteps());
    }

    @And("I save phase to Phase library")
    public void phaseSelection() {
        recipePage.phaseSelection(this.recipe.getPhaseName());
        recipeTouch.buttonClick("Save Phase Library");
    }

    @And("I should see phase details under phase library")
    public void verifyPhaseInLibrary(){
        recipePage.expandPhaseLibrary();
        recipeTouch.verifyPhaseInLibrary(this.recipe.getPhaseName());
    }
    
    @And("I Delete phase")
    public void deletePhase() {
        recipePage.phaseSelection(this.recipe.getPhaseName());
        recipeTouch.buttonClick("Delete Phase");
        recipePage.handleWarningPopUp(this.recipe.getPhaseName());
    }

    @And("I add 2 phases with Steps {string} & {string}")
    public void addMultiplePhases(String phaseOneSteps, String phaseTwoSteps){
        addingPhase(phaseOneSteps);
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhaseAndVerifySuccessMessage(this.recipe.getPhaseName());
        recipePage.verifyPhaseName(this.recipe.getPhaseName());
        addingPhase(phaseTwoSteps);
        this.recipe.setSecondPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhaseAndVerifySuccessMessage(this.recipe.getSecondPhaseName());
        recipePage.verifyPhaseName(this.recipe.getSecondPhaseName());
    }

    public void addingPhase(String stepNo){
        this.recipe.setPhaseActionSteps((stepNo));
        recipePage.selectStep(stepNo);
        recipeTouch.buttonClick("Add Phase");
        recipePage.phaseCreationNotification();
    }

    @When("I select {string} button")
    public void buttonSelection(String value){
        recipeTouch.buttonClick(value);
    }

    @And("I Import Recipe")
    public void importRecipe(){
        buttonSelection("Import");
        recipePage.importRecipeSelection(recipe.getRecipeName());
        recipe.setRecipeImportedName(recipePage.getGeneratedName());
    }

    @And("I SaveAs recipe {string}")
    public void saveAsRecipe(String recipeName){
        recipePage.saveAsButton(recipeName);
    }

    @And("I {string} number {int}")
    public void insertStep(String action, int stepNo){
        recipePage.selectStep(Integer.toString(stepNo));
        recipeTouch.buttonClick(action);
    }

    @And("I add action to new blank step")
    public void addActionToBlankStep(){
        recipePage.placeholder("blank");
        recipePage.addActionStep(recipe.getOrgStepCount()+1);
    }
}