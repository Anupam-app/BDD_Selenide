package cucumber.steps;

import com.codeborne.selenide.Selenide;


import dataobjects.Analytics;
import dataobjects.Recipe;
import dataobjects.Report;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

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
		this.currentRecipe = new Recipe();
		this.currentRecipe.setRecipeName(recipe);
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
	public void recipeExecIsPaused() throws ParseException {
		recipeConsolePage.recipeisPaused();
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
		String runId = recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
		this.currentRecipe.setRunId(runId);
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

	@And("I wait the end of the execution of the recipe during {int} seconds")
	public void iWaitTheEndOfTheExecutionOfTheRecipe(int seconds) {
		recipeConsolePage.isExecuted(seconds);
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
		Selenide.sleep(1000);
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

	@Then("I should see the recipe run {string}")
	public void iVerifyRecipeAbort(String status) throws InterruptedException {
		if (status.equalsIgnoreCase("Aborted")) {
			Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
			recipeConsolePage.clickOnOk();
		} else if (status.equalsIgnoreCase("Completed")) {
			Assert.assertEquals("Completed", this.recipeConsolePage.getExecutionStatusText());
			recipeConsolePage.clickOnOk();
		}
	}

	@And("I clear the recipe")
	public void clearRecipePanel() {
		recipeConsolePage.clearRecipe();
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

	@Then("I should see the recipe run aborted")
	public void iVerifyRecipeAbort() {
		Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
		recipeConsolePage.clickOnOk();
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
		generateRecipeValues("", "");
		recipeConsolePage.startRerunRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
	}

	@When("I Process hold the system")
	public void processHold() {
		recipeConsolePage.holdSystem();

	}

	@And("I verify Manual Operation tab is {string}")
	public void manualOperation(String status) {
		recipeConsolePage.manualOperation(status);
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
		if (status.equalsIgnoreCase("Manual operation")) {
			if (true) {
				recipeConsolePage.manualOperation("enabled");
			}
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
		generateRecipeValues(null, null);
		recipeConsolePage.manualRunStart(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
	}

	@Then("I validate the timer and stop button and run details")
	public void validateRun_Timer_Stop() {
		recipeConsolePage.validationOfRunDetails();
		Assert.assertTrue(recipeConsolePage.verifyStopButton());
		recipeConsolePage.timeValidation();
	}

	@And("I validate the timer is incrementing")
	public void timerIncrementCheck() {
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

	@Then("I should see Process restart button")
	public void restartButton() {
		Assert.assertTrue(recipeConsolePage.verifyRestart());
	}

	@And("I validate the Start button is {string}")
	public void afterPostRunAutoClose(String status) {
		recipeConsolePage.validateStartButtonNotSelect(status);
	}

	@Then("I should not see unapproved recipe")
	public void iverifyunApprovedRecipe() {
		Assert.assertTrue(recipeConsolePage.verifyApprovedRecipe());
		recipeConsolePage.clickOnClose();
	}

	@Then("I should see recipe name and recipe steps details")
	public void iverifyRecipeNameandRecipeSteps() {
		Assert.assertTrue(recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId()));
	}

	@Then("I should see start button is displayed")
	public void iSeeStartButton() {
		recipeConsolePage.iValidateStart();
	}

	@When("I click on start button")
	public void iclickOnStartButton() {
		recipeConsolePage.start_button();
	}

	@Then("I close post run window and select re run option")
	public void iseepreRunWindowPopup() {
		recipeConsolePage.clickOnOk();
		recipeConsolePage.reRun();
		recipeConsolePage.preRunWindow_Popup();
	}

	@When("I click ok button")
	public void iClickOkButton() {
		recipeConsolePage.okButton();
	}

	@Then("I should see {string} message")
	public void iseemessage(String message) {
		recipeConsolePage.validateHilightedMsg(message);
	}

	@When("I enter existing value in RUNID")
	public void iEnterExistingValueInRunId() {
		recipeConsolePage.runIdManual(this.currentRecipe.getRunId());
	}

	@Then("I should see message {string}")
	public void iShouldSeeMessage(String message) {
		recipeConsolePage.validateMsg(message);
	}

	@And("I Verify manual run status in recipe consol")
	public void iVerifyRecipeRunStatus() {
		generateRandomRecipeValues();
		recipeConsolePage.manualValidation(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(),
				this.currentRecipe.getProductId(), this.currentRecipe.getBeforeComments());
	}

	@When("I enter special characters {string} in comments section")
	public void iValidateSpecialChar(String specialchar) {
		generateRandomRecipeValues();
		recipeConsolePage.iValidateSpecialChar_manaul(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(),
				this.currentRecipe.getProductId(), specialchar);
	}

	@When("I enter special characters {string} in run comments section")
	public void iValidateRunRecipeComments(String specialchar) {
		generateRandomRecipeValues();
		recipeConsolePage.iValidateSpecialChar_run(this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(),
				this.currentRecipe.getProductId(), specialchar);
	}

	@Then("I should not see special characters not allowed")
	public void iShouldnotSeeSpecialCharacters() {
		recipeConsolePage.iValidationPreRun();
	}

	@Given("I click on load recipe")
	public void clickOnLoadRecipe() {
		recipeConsolePage.clickOnLoadRecipe();
	}

	@Then("I should see again recipe name and recipe steps details")
	public void iVerifyStepsInConsol() {
		recipeConsolePage.verifyLivestepDetails(this.currentRecipe.getSteps());
	}

	@And("I go to Main screen")
	public void gotoMainPage() {
		SelenideHelper.goToDefault();
		recipeConsolePage.mainPage();
	}

	@And("I run the recipe")
	public void runRecipe(int seconds) {
		recipeConsolePage.clickOnLoadRecipe();
		generateRandomRecipeValues();
		String runId = recipeConsolePage.startAndWaitRecipe(this.currentRecipe, seconds);
		currentRecipe.setRunId(runId);
		recipeConsolePage.isExecuted();
	}

	@And("I verify the recipe execution details in console View")
	public void recipeDetailsInConsole() {
		recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId());
	}

	@And("I select run recipe")
	public void iSelectrunRecipe() {
		recipeConsolePage.run_Btn();
	}

	@When("I expand recipe console")
	public void iExpandRecipeConsole() {
		recipeConsolePage.gotoRecipeConsole();
	}

	@And("I verify the recipe execution details in console View.")
	public void iVerifyRecipeExcution() {
		recipeConsolePage.expandRecipeConsole();
		recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId());
	}

	@And("I verify the Batch ID suggestion with unique Value")
	public void iVerifyBatchidWithUniqueValue() {
		generateRandomRecipeValues();
		recipeConsolePage.uniqBatchId(this.currentRecipe.getBatchId());
	}

	@When("I Select Process Hold")
	public void iSelectedProcessHold() {
		recipeConsolePage.processHold();
	}

	@And("I verify the Process hold Dialog box , buttons")
	public void iVerifyProcessHoldDailogBox() {
		recipeConsolePage.iverifyDailogBox();
	}

	@And("I validate close,No button funtionality")
	public void iVerifyCloseAndNobuttonFunctionality() {
		recipeConsolePage.validateNoBtn();
	}

	@And("I Select Yes button")
	public void iSelectYesButton() {
		recipeConsolePage.validateYesBtn();
	}

	private void generateRandomRecipeValues() {
		this.currentRecipe.setProductId(RandomStringUtils.randomAlphabetic(10));
		this.currentRecipe.setBatchId(RandomStringUtils.randomAlphabetic(10));
		this.currentRecipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
		this.currentRecipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
		this.currentRecipe.setManualOperationName(RandomStringUtils.randomAlphabetic(10));
		this.currentRecipe.setRunId(RandomStringUtils.randomAlphabetic(10));
	}

	@And("I refresh the portal")
	public void iRefreshThePortal() {
		recipeConsolePage.iRefreshPortal();
		Selenide.sleep(2000);
		SelenideHelper.maximize();
	}

	@Then("I should see change of Process holding to Process restart")
	public void iSeeChangeProcessHoldingToProcessRestart() {
		recipeConsolePage.processRestart();
	}

	@And("I verify the recipe console Elements")
	public void iVerifyRecipeConsoleElements() {
		recipeConsolePage.iVerifyRecipeConsoleElement();
	}

	@And("I select Process restart")
	public void iSelectProcessRestart() {
		recipeConsolePage.iSelectProcessRestart();
	}

	@And("I verify the Process restart Dialog box , buttons")
	public void iVerifyProcessRestartDailogBox() {
		recipeConsolePage.iVerifyProcessRestartPopup();
	}

	@Then("I should see change of Process restating to Process hold")
	public void iSeeProcessRestartingToProcessHold() {
		recipeConsolePage.iVerifyProcessRestartToProcessHold();
	}

	@When("I load recipe {string} and run it during {int} seconds if not done before")
	public void iStartAndWaitRecipeExecutionIfNotRunBefore(String recipe, int seconds) {
		iGotoRecipeConsole();
		if (!recipeConsolePage.isRunBefore(recipe)) {
			iLoadRecipeAndIStartIt(recipe, seconds);
		}
	}

	@And("I verify all mandatory fields has asterick mark {string}")
	public void iVerifyAllMandatoryFieldsHasAsterickMark(String mark) {
		recipeConsolePage.reRun();
		recipeConsolePage.verifyAsterickMark(mark);
	}

	@And("I start manual recipe execution")
	public void iStartManualRecipeExecution() {
		generateRandomRecipeValues();
		recipeConsolePage.manualValidation(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(),
				this.currentRecipe.getProductId(), this.currentRecipe.getBeforeComments());
		recipeConsolePage.okButton();
		recipeConsolePage.stopBtn();
		recipeConsolePage.validateYesBtn();
	}

	@And("I see Recipe should be executed")
	public void iSeeRecipeShouldBeExecuted() {
		Assert.assertTrue(recipeConsolePage.iCheckrecipeDetails(this.currentRecipe.getBatchId(),this.currentRecipe.getRunId()));
	}

	@And("I wait for {int} sec and stop the run")
	public void iWaitAndStopManualOperation(int seconds) {
		recipeConsolePage.startAndWaitManualOperation(seconds);
		recipeConsolePage.clickOnOk();		
	}

	@When("I load recipe {string} and select run button")
	public void iLoadRecipeAndRunTheRecipe(String recipe, int seconds) {
		iGotoRecipeConsole();
		iLoadRecipeAndIStartIt(recipe,seconds);
	}

	@Then("I should see pre run window")
	public void iSeePreRunWindow() {
		recipeConsolePage.run_Btn();
		recipeConsolePage.preRunWindow_Popup();
	}

	@When("I clear and try to enter lenghty RUN ID, BatchID")
	public void iEnterLenghtyRunIdAndBatchID() {
		this.currentRecipe.setRunId(RandomStringUtils.randomAlphabetic(50));
		recipeConsolePage.runIdManual(this.currentRecipe.getRunId());
		this.currentRecipe.setBatchId(RandomStringUtils.randomAlphabetic(45));
		recipeConsolePage.uniqBatchId(this.currentRecipe.getBatchId());
	}

	@And("provide remaining mandatory data to select OK button")
	public void provideRemainingData() {
		generateRandomRecipeValues();
		recipeConsolePage.iProvidingData(this.currentRecipe.getProductId(),this.currentRecipe.getBeforeComments());
	}

	@And("I try to stop the manual run")
	public void StopManualRun() {
		recipeConsolePage.istopManual();
	}

	@And("I select No option to continue manual run execution")
	public void iSelectNoOption() {
		recipeConsolePage.iClickNoInEndManualOperation();
		recipeConsolePage.validateNoBtn();   	   	
	}

	@And("I select X option to continue manual run execution")
	public void iClickXInManualReunExecution() {
		recipeConsolePage.iClickNoInEndManualOperation();
		recipeConsolePage.iQuitEndManualOperation();
	}

	@And("I select yes option to continue manual run execution")
	public void iSelectYesButtonINManualRunExecution() {
		recipeConsolePage.iClickNoInEndManualOperation();
		recipeConsolePage.validateYesBtn();
	}

	@And("I verify the recipe name displayed on load recipe list")
	public void iVeifyTheRecipeNameDisplayed() {
		recipeConsolePage.recipenameDisplayed();
	}

	@Then("I verify the recipe name is trimmed on recipe console UI")
	public void iVerifyUiFielsOfManualOperations() {
		recipeConsolePage.iVerifyRcipeName();
		recipeConsolePage.iVerifyTrimmedRecipe();
		recipeConsolePage.iVerifyConditionalStatement();
	}

	@And("I verify the recipe lengthy step is trimmed")
	public void iCheckRecipeStepsTrimmed() {
		generateRecipeValues(null, null);
		String runId = recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
		this.currentRecipe.setRunId(runId);
	}

	@Then("I verify mouse hover on step displays tool tip with full step details")
	public void iVerifyMouseHoverStepDetails() {
		recipeConsolePage.iCheckStepDetailsWithMouseHover();
	}

	@And("I should see recipe execution started succesfully")
	public void iSeeRecipeExecution() {
		recipeConsolePage.iVerifyRecipeExecution();
	}

	@And("I validate the recipe console UI elements")
	public void iValidateTheUIElements() {
		recipeConsolePage.pauseButton();
		recipeConsolePage.verifyAbortButton();
	}

	@And("I mouse hover RUNID and BatchID to validate full text displayed")
	public void iCheckBatchIdAndRunIdWithMouseHover() {
		recipeConsolePage.iCheckStepDetailsWithMouseHover();
	}

	@And("I Abort the recipe execution")
	public void iAbortTheRecipeExecution() {
		recipeConsolePage.clickOnAbortButton(this.currentRecipe.getAfterComments());
	}

	@And("I validate the RUNID BATCHID text displayed on Post run window")
	public void iValidatePostRunWindow() {
		recipeConsolePage.iDisplayedRunIdAndBatchId();
	}

	@And("I verify the post run window UI fields")
	public void iVerifyUIFields() throws ParseException {
		recipeConsolePage.verifyPostRunDate();
	}

	@And("I close the post run window")
	public void iCloseTheWindow() {
		recipeConsolePage.autoClosePostRun();
	}

	@Then("I see process hold button is displayed")
	public void iSeeProcessHoldButton() {
		recipeConsolePage.iverifyDailogBox();
	}

	@Then("Verify the recipe console extended view UI components")
	public void iVerifyUIComponents() {
		recipeConsolePage.gotoRecipeConsole();
		recipeConsolePage.iVerifyRecipeConsoleElement();
		recipeConsolePage.checkDiable_Btns();
	}

	@And("I see recipe execution is paused")
	public void recipeExcutionPaused() {
		recipeConsolePage.iVerifyRecipePause();
	}

	@Given("I load the recipe {string}")
	public void iLoadTheRecipe(String recipe) {
		this.currentRecipe = new Recipe();
		this.currentRecipe.setRecipeName(recipe);
		recipeConsolePage.iLoadRecipelink(recipe);
	}
}