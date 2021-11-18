package cucumber.steps;

import dataobjects.Recipe;
import dataobjects.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipePage;

import static pageobjects.utility.SelenideHelper.goToIFrame;

public class RecipePageStepsDefinition {

    private RecipePage recipePage;
    private Recipe recipe;
    private User user;

    public RecipePageStepsDefinition(RecipePage recipePage, Recipe recipe,User user) {
        this.recipePage = recipePage;
        this.recipe = recipe;
        this.user = user;
    }

    @Given("I go to recipe page")
    public void iGoToRecipePage() {
        recipePage.goTo();
        goToIFrame();
    }

    @Then("I search the recipe")
    public void iSearchRecipe() {
        recipePage.setSearch(this.recipe.getRecipeName());
    }

    @And("I edit the recipe")
    public void iEditTheRecipe() {
        recipePage.edit(this.recipe.getRecipeName());
    }

    @And("I delete all phases")
    public void iDeleteAllPhases() {
        recipePage.deleteAllPhases();
    }

    @When("I trigger edit mode")
    public void iGoToEditMode() {
        recipePage.goToEditMode();
    }

    @Then("I go to browser mode")
    public void iGoToBrowserMode() {
        recipePage.goToBrowserMode();
    }

    @When("I create a random phase")
    public void iCreateARandomPhase() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
    }

    @And("I save the recipe")
    public void iSaveTheRecipe() {
        this.recipe.setRecipeName(RandomStringUtils.randomAlphabetic(10));
        recipePage.saveRecipe(this.recipe.getRecipeName());
    }

    @And("I see my changes in recipe")
    public void iSeeMyChangesInRecipe() {
        Assert.assertEquals(this.recipePage.getPhaseName(), this.recipe.getPhaseName());
    }

    @Given("I goto to editor tab in recipe page")
    public void iNevigateToEditorTab() {
        recipePage.naviagateToEditorTab();
    }

    @When("I create a recipe {string} with phase")
    public void iCreateARecipe(String recipe) {
        this.recipe.setRecipeNode(recipe);
        recipePage.createRecipe(recipe);
    }

    @And("I save the recipe as {string}")
    public void iSaveRecipeAs(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.saveRecipe(recipe);
    }

    @Then("I save the modified recipe")
    public void iSaveModifiedRecipeAs() {
        recipePage.saveModifiedRecipe();
    }


    @Then("I see recipe {string} is created with draft status in browser tab")
    public void iVerifyRecipeIsCreated(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.verifyRecipe(recipe);
        Assert.assertEquals(this.recipe.getRecipeName(),this.recipePage.getRecipeName());
    }


    @Given("I edit recipe {string}")
    public void iEditRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.editRecipe(recipe);
    }

    @When("I delete phase to recipe")
    public void iDeletePhaseToRecipe() {
        recipePage.deletePhaseToRecipe();
    }

    @When("I add phase to recipe")
    public void iAddPhaseToRecipe() {
        this.recipe.setPhaseName(RandomStringUtils.randomAlphabetic(10));
        recipePage.addPhase(this.recipe.getPhaseName());
    }

    @When("I approve recipe")
    public void iApproveRecipe() {
        recipePage.approveRecipe(user.getPassword());
    }

    @Then("Recipe should be approved")
    public void recipeShouldBeApproved() {
        Assert.assertEquals("Approved-Active",this.recipePage.getStatus());
    }


    @Given("I expand recipe console in pnid")
    public void iGotoRecipeConsole() {
        recipePage.gotoRecipeConsole();
    }

    @Given("I load recipe {string}")
    public void iLoadRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipePage.loadRecipe(recipe);
    }

    @When("I start recipe execution with project id {string}")
    public void iStartRecipeExecution(String recipe) {
        this.recipe.setProductId(recipe);
        this.recipe.setBatchId(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
        recipePage.recipeExecution(recipe,this.recipe.getBatchId(),this.recipe.getBeforeComments(),this.recipe.getAfterComments());
    }

    @Then("Recipe should be executed")
    public void recipeExecuted() {
        recipePage.isExecuted();
    }

}
