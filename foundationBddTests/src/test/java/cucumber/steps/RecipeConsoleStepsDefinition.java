package cucumber.steps;

import cucumber.util.I18nUtils;
import dataobjects.Analytics;
import dataobjects.Recipe;
import dataobjects.Report;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;

import pageobjects.pages.RecipeConsolePage;
import pageobjects.utility.SelenideHelper;

public class RecipeConsoleStepsDefinition {

	private RecipeConsolePage recipeConsolePage;
	private Recipe currentRecipe;
	private List<Recipe> recipes;
	private Report report;
	private Analytics analytics;

	public RecipeConsoleStepsDefinition(RecipeConsolePage recipeConsolePage, Report report, Analytics analytics, Recipe currentRecipe) {

		this.recipeConsolePage = recipeConsolePage;
		this.recipes = new ArrayList<>();
		this.report = report;
		this.analytics = analytics;
		this.report.setRecipes(this.recipes);
		this.analytics.setRecipes(this.recipes);
		this.currentRecipe = currentRecipe;
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
	
	@Given("I load recipe")
	public void iLoadRecipe() {
		recipeConsolePage.openLoadRecipePage();
	}
	
	@Then("I verify the details")
	public void iVerifyTooltip() {
		recipeConsolePage.verifyTooltipLoadRecipePage();
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
	
	@When("I rerun recipe execution and timer starts from zero")
	public void iRerunRecipeExecution() throws ParseException {
		generateRecipeValues(null, null);
		recipeConsolePage.reRunRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
	}
	
	@When("I provide special chars in pre run comments")
	public void iStartRecipeExecutionWithSpecialChars() {
		generateRecipeValues(null, null);
		recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), "$%^%(&^&");
	}
	
	@Then("I see the error message as {string}")
	public void iSeeErrorMessage(String message) {
		recipeConsolePage.verifyCommentErrorMessage(message);
	}

	private void generateRecipeValues(String batchId, String productId) {
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
	
	@When("I wait for {int} seconds")
	public void iWait(int seconds) {
		Selenide.sleep(seconds*1000);
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

	@Then("I see expected texts from recipe console")
	public void iSeeExpectedTextsFromRecipeConsole() {
		var expectedRestartText = I18nUtils.getValueFromKey("portal.recipeConsole.button.processRestart");
		var expectedHoldText = I18nUtils.getValueFromKey("portal.recipeConsole.button.processHold");
		var restartOrHoldContent = recipeConsolePage.seeRestartOrHoldContent();
		Assert.assertTrue(restartOrHoldContent.equals(expectedRestartText) || restartOrHoldContent.equals(expectedHoldText));
		SelenideHelper.goParentFrame();
	}

	@And("I clear the recipe")
	public void clearRecipePanel() {
		recipeConsolePage.clearRecipe();
	}

	@When("I load recipe {string} and run it during {int} seconds if not done before")
	public void iStartAndWaitRecipeExecutionIfNotRunBefore(String recipe, int seconds) {
		iGotoRecipeConsole();
		if (!recipeConsolePage.isRunBefore(recipe)) {
			iLoadRecipeAndIStartIt(recipe, seconds);
		}
	}

	@Then("Recipe execution is paused")
	public void recipeExecIsPaused() throws ParseException{
		recipeConsolePage.recipeisPaused();
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

	@Given("I goto manual operation tab")
	public void iGoToManualOprtationTab() {
		recipeConsolePage.gotoManualOperations();
	}

	@Given("I verify manual operation options")
	public void iVerifyManualRunOptions() {
		recipeConsolePage.verifyManualRunOptions();
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

	@And("I wait the end of the execution of the recipe during {int} seconds")
	public void iWaitTheEndOfTheExecutionOfTheRecipe(int seconds) {
		recipeConsolePage.isExecuted(seconds);
	}

	@Then("I should see the recipe run {string}")
	public void iVerifyRecipeAbort(String status) throws InterruptedException {
		if(status.equalsIgnoreCase("Aborted")) {
			Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
			recipeConsolePage.clickOnOk();
		}
		else if(status.equalsIgnoreCase("Completed")) {
			Assert.assertEquals("Completed", this.recipeConsolePage.getExecutionStatusText());
			recipeConsolePage.clickOnOk();
		}
	}

	@Then("I should see error message about recipe step")
	public void errorMessageOfJumpStep() throws InterruptedException {
		recipeConsolePage.jumpStepErrorMessage();
		recipeConsolePage.clickOnAbortButton(this.currentRecipe.getAfterComments());
	}

	@Then("I jump to Step no and verify step execution")
	public void stepJumpAndStepVerifyExecution(DataTable table) {
		List<String> list = table.asList(String.class);
		for (int i = 1; i < list.size(); i++) {
			recipeConsolePage.verifyStep(list.get(i));
		}
	}

	@And("I verify Manual Operation tab is {string}")
	public void manualOperation(String status) {
		recipeConsolePage.manualOperation(status);
	}

	@And("I pause recipe and verify recipe paused and jump icon is disabled")
	public void pauseButton() {
		recipeConsolePage.clickPauseButton();
		ctrlOnResumeButton();
		recipeConsolePage.verifyJumpStep();
	}

	@Then("recipe execution is resumed")
	public void validationRecipeExecution() {
		recipeConsolePage.pauseButton();
	}

	@When("I re-run the recipe")
	public void reRunRecipe() {
		recipeConsolePage.reRun();
		generateRecipeValues("","");
		recipeConsolePage.startRerunRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
	}

	@When("I Process hold the system")
	public void processHold() {
		recipeConsolePage.holdSystem();	
	}
	
	@Then("I verify Recipe Run tab is {string}")
	public void recipeRun(String status) {
		recipeConsolePage.recipeRun(status);
	}
	@And("I restart the Process hold")
	public void restartProcess() {
		recipeConsolePage.restartSystem();
	}
	@When("I select {string} tab")
	public void recipeOperation(String status) {
		if(status.equalsIgnoreCase("Manual operation")) {
			recipeConsolePage.manualOperation("enabled");
		}
	}
	@When("I resume and verify recipe execution is resumed")
	public void resumeStatus() {
		recipeConsolePage.clickResumeButton();
		ctrlOnPauseButton();   
	}
	//we enchance the code on merging (Run, Rerun & Start)
	@And("I start Manual run")
	public void manualRun() {
		generateRecipeValues(null,null);
		recipeConsolePage.manualRunStart(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
	} 
	@Then("I validate the timer and stop button and run details")
	public void validateRun_Timer_Stop() {
		recipeConsolePage.validationOfRunDetails();
		Assert.assertTrue(recipeConsolePage.verifyStopButton());
		recipeConsolePage.timeValidation();
	}
	@Then("I should see Process restart button")
	public void restartButton() {
		Assert.assertTrue(recipeConsolePage.verifyRestart());
	}
	@And("I validate the timer is incrementing")
	public void timerIncrementCheck(){
		recipeConsolePage.incrementTimer();
	}
	@When("I Stop the RUN")
	public void stopRun() {
		recipeConsolePage.stopButton();
	}
	@Then("I validate the date formats in Post run window and enter comments")
	public void validatePostRun() throws ParseException {
		recipeConsolePage.verifyPostRunDate();
	}
	@And("I wait for 1 min for the post run window to auto closed")
	public void verifyAutoClosePostRun() {
		recipeConsolePage.autoClosePostRun();
	}
	@And("I validate the Start button is {string}")
	public void afterPostRunAutoClose(String status) {
		recipeConsolePage.validateStartButtonNotSelect(status);
	}

	@And("I wait until Run button is displayed and {string}")
	public void runButtonDisplayed(String value) {
		recipeConsolePage.runButton(value);
	}
}
