package cucumber.steps;

import dataobjects.Recipe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipeConsolePage;

public class RecipeConsoleStepsDefinition {

    private RecipeConsolePage recipeConsolePage;
    private Recipe recipe;

    public RecipeConsoleStepsDefinition(RecipeConsolePage recipeConsolePage, Recipe recipe) {
        this.recipeConsolePage = recipeConsolePage;
        this.recipe = recipe;
    }

    @Given("I expand recipe console in pnid")
    public void iGotoRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
    }

    @Given("I load recipe {string}")
    public void iLoadRecipe(String recipe) {
        this.recipe.setRecipeName(recipe);
        recipeConsolePage.loadRecipe(recipe);
    }

    @When("I hold and restart the system")
    public void iHoldAndRestartTheSystem() {
        recipeConsolePage.holdAndRestart();
    }

    @When("I start and wait recipe execution during {int} seconds")
    public void iStartAndWaitRecipeExecution(int seconds) {
        generateRandomRecipeValues();
        recipeConsolePage.startAndWaitRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments(), this.recipe.getAfterComments(), seconds);
    }

    @When("I load recipe {string} and run it during {int} seconds if not done before")
    public void iStartAndWaitRecipeExecutionIfNotRunBefore(String recipe, int seconds) {
        if (!recipeConsolePage.isRunBefore(recipe)) {
            iLoadRecipe(recipe);
            iStartAndWaitRecipeExecution(seconds);
        }
    }

    @When("I start recipe execution")
    public void iStartRecipeExecution() {
        generateRandomRecipeValues();
        recipeConsolePage.startRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments());
    }

    private void generateRandomRecipeValues() {
        this.recipe.setProductId(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setBatchId(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
    }

    @Then("Recipe should be executed")
    public void recipeExecuted() {
        recipeConsolePage.isExecuted();
    }

    @When("I click on pause button")
    public void iClickOnPauseButton() {
        recipeConsolePage.clickPauseButton();
    }

    @When("I click on resume button")
    public void iClickOnResumeButton() {
        recipeConsolePage.clickResumeButton();
    }

    @When("I click on jump step {string}")
    public void iClickOnJumpToStep(String stepNumber) {
        recipeConsolePage.clickOnJumpToStep(stepNumber);
    }

    @When("I click on abort button")
    public void iClickOnAbortButton() {
        recipeConsolePage.clickOnAbortButton(this.recipe.getAfterComments());
    }

    @Then("I see the system on hold")
    public void iSeeTheSystemOnHold() {
        recipeConsolePage.seeSystemOnHold();
    }

    @Then("I should see the recipe run aborted")
    public void iVerifyRecipeAbort() {
        Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
        recipeConsolePage.clickOnOk();
    }
}