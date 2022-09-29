package cucumber.steps;

import dataobjects.Recipe;
import io.cucumber.java.en.And;
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
        String runId = recipeConsolePage.startAndWaitRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments(), this.recipe.getAfterComments(), seconds);
        recipe.setRunId(runId);
    }

    @When("I load recipe {string} and run it during {int} seconds if not done before")
    public void iStartAndWaitRecipeExecutionIfNotRunBefore(String recipe, int seconds) {
        iGotoRecipeConsole();
        if (!recipeConsolePage.isRunBefore(recipe)) {
            iLoadRecipeAndIStartIt(recipe, seconds);
        }
    }

    private void iLoadRecipeAndIStartIt(String recipe, int seconds) {
        iLoadRecipe(recipe);
        iStartAndWaitRecipeExecution(seconds);
    }

    @When("I load recipe {string} and run it during {int} seconds")
    public void iStartAndWaitRecipeExecution(String recipe, int seconds) {
        iGotoRecipeConsole();
        iLoadRecipeAndIStartIt(recipe, seconds);
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

    @And("I wait the end of the execution of the recipe")
    public void iWaitTheEndOfTheExecutionOfTheRecipe() throws InterruptedException {
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
    @When("I start and wait second recipe execution with {string} batchID during {int} seconds")
    public void iStartAndWaitRecipeExecutionSecondRecipe(String value,int seconds) {
    	 
    	String runId = null;
    	if(value.equalsIgnoreCase("new")) {
    		generateRandomRecipeValues();
            runId = recipeConsolePage.startAndWaitRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments(), this.recipe.getAfterComments(), seconds);
            recipe.setRunId(runId);
    	}
    	else if(value.equalsIgnoreCase("different"))
    	{
    		generateRandomRecipeValues_SecondRecipe();
    		runId = recipeConsolePage.startAndWaitRecipe(this.recipe.getProductId2(), this.recipe.getBatchId2(), this.recipe.getBeforeComments(), this.recipe.getAfterComments(), seconds);
    		recipe.setRunId2(runId);
    	}
    	
    	else if(value.equalsIgnoreCase("same"))
    	{
    		runId = recipeConsolePage.startAndWaitRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments(), this.recipe.getAfterComments(), seconds);

    		recipe.setRunId3(runId);
    	}
    }
    private void generateRandomRecipeValues_SecondRecipe() {
        this.recipe.setProductId2(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setBatchId2(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
        this.recipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
    }
    @And("I clear the recipe")
	public void clearRecipePanel() {
    	 recipeConsolePage.clearRecipe();
	}
}
