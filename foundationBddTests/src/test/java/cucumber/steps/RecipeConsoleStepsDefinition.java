package cucumber.steps;

import dataobjects.Recipe;
import dataobjects.Report;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RecipeConsolePage;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.UserPage;

public class RecipeConsoleStepsDefinition {

    private RecipeConsolePage recipeConsolePage;
    private Recipe recipe;
    private final Report report;
    private final ReportsPage reportPage;

    public RecipeConsoleStepsDefinition(ReportsPage reportPage, Report report,RecipeConsolePage recipeConsolePage, Recipe recipe) {
        this.recipeConsolePage = recipeConsolePage;
        this.recipe = recipe;
        this.report = report;
        this.reportPage = reportPage;
    }

    @Given("I expand recipe console in pnid")
    public void iGotoRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
    }
    
    @Given("I expand and collapse recipe console in pnid")
    public void iExpandAndCollapseRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
        recipeConsolePage.collapseRecipeConsole();
        recipeConsolePage.gotoRecipeConsole();
    }
    
    @Given("I verify Recipe run options")
    public void iVerifyRecipeRunOptions() {
        recipeConsolePage.verifyRecipeRunOptions();
    }
    
    @Given("I goto manual operation tab")
    public void iGoToManualOprtationTab() {
        recipeConsolePage.gotoManualOperations();
    }
    
    @Given("I verify manual operation options")
    public void iVerifyManualRunOptions() {
        recipeConsolePage.verifyManualRunOptions();
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
	
	@When("I hold the system")
    public void iHoldTheSystem() {
        recipeConsolePage.hold();
    }
	
	@When("I restart the system")
    public void iRestartTheSystem() {
        recipeConsolePage.restartSystem();
    }
	
	@Then("clear panel and run button is disabled")
    public void iSeeTheClearPanelAndRunDisabled() {
        recipeConsolePage.clearPanelAndRunDisabled();
    }
	
	@Then("Recipe execution is paused")
    public void recipeExecIsPaused() throws ParseException{
        recipeConsolePage.recipeisPaused();
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
    
    @When("I check audit trial logs")
    public void icheckAudiTrialLogs() {
        reportPage.checkRecipeCTRLOperationLogs(this.recipe.getBatchId(),this.recipe.getRecipeName());
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
