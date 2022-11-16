package cucumber.steps;

import dataobjects.Recipe;
import dataobjects.Report;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipeConsolePage;

public class RecipeConsoleStepsDefinition {

    private RecipeConsolePage recipeConsolePage;
    private Recipe currentRecipe;
    private List<Recipe> recipes;
    private Report report;

    public RecipeConsoleStepsDefinition(RecipeConsolePage recipeConsolePage, Report report) {
        this.recipeConsolePage = recipeConsolePage;
        this.recipes = new ArrayList<>();
        this.report=report;
        this.report.setRecipes(this.recipes);
    }

    @Given("I expand recipe console in pnid")
    public void iGotoRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
    }

    @Given("I load recipe {string}")
    public void iLoadRecipe(String recipe) {
        this.currentRecipe = new Recipe();
        this.currentRecipe.setRecipeName(recipe);
        recipeConsolePage.loadRecipe(recipe);
    }

    @When("I hold and restart the system")
    public void iHoldAndRestartTheSystem() {
        recipeConsolePage.holdAndRestart();
    }

    private void iLoadRecipeAndIStartIt(String recipe, int seconds) {
        iLoadRecipe(recipe);
        iStartAndWaitRecipeExecution(seconds);
    }

    private void iLoadRecipeAndIStartIt(String recipe, int seconds, String batchId, String productId) {
        iLoadRecipe(recipe);
        iStartAndWaitRecipeExecution(seconds, batchId, productId);
    }

    @When("I start and wait recipe execution during {int} seconds")
    public void iStartAndWaitRecipeExecution(int seconds) {
        iStartAndWaitRecipeExecution(seconds, null, null);
    }

    public void iStartAndWaitRecipeExecution(int seconds, String batchId, String productId) {
        generateRecipeValues(batchId, productId);
        String runId = recipeConsolePage.startAndWaitRecipe(this.currentRecipe, seconds);
        currentRecipe.setRunId(runId);
        recipes.add(currentRecipe);
    }

    @When("I load recipe {string} and run it during {int} seconds")
    public void iStartAndWaitRecipeExecution(String recipe, int seconds) {
        iGotoRecipeConsole();
        iLoadRecipeAndIStartIt(recipe, seconds);
    }

    @When("I load recipe {string} and run it during {int} seconds with batch id {string} and product id {string}")
    public void iStartAndWaitRecipeExecution(String recipe, int seconds, String batchId, String productId) {
        iGotoRecipeConsole();
        iLoadRecipeAndIStartIt(recipe, seconds, batchId, productId);
    }

    @When("I start recipe execution")
    public void iStartRecipeExecution() {
        generateRecipeValues(null, null);
        recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
    }

    private void generateRecipeValues(String batchId,String productId) {
        if (StringUtils.isNotEmpty(productId)) {
            this.currentRecipe.setProductId(productId);
        } else {
            this.currentRecipe.setProductId(RandomStringUtils.randomAlphabetic(10));
        }

        if (StringUtils.isNotEmpty(batchId)) {
            this.currentRecipe.setBatchId(batchId);
        } else {
            this.currentRecipe.setBatchId(RandomStringUtils.randomAlphabetic(10));
        }

        this.currentRecipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
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
    
    @Then("control should be on resume button")
    public void ctrlOnResumeButton() {
        Assert.assertTrue(recipeConsolePage.verifyResumeButton());
    }
    
    @Then("control should be on pause button")
    public void ctrlOnPauseButton() {
        Assert.assertTrue(recipeConsolePage.verifyPauseButton());
    }
    
    @Then("control should be on rerun button")
    public void ctrlOnrerunButton() {
        Assert.assertTrue(recipeConsolePage.verifyReRunButton());
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
        recipeConsolePage.clickOnAbortButton(this.currentRecipe.getAfterComments());
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

    @And("I clear the recipe")
    public void clearRecipePanel() {
        recipeConsolePage.clearRecipe();
    }
}
