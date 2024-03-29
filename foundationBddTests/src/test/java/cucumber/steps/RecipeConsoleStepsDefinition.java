package cucumber.steps;

import com.codeborne.selenide.Selenide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import cucumber.util.I18nUtils;
import dataobjects.Analytics;
import dataobjects.Recipe;
import dataobjects.Report;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.pages.RecipeConsolePage;
import pageobjects.pages.ReportsPage;
import pageobjects.utility.SelenideHelper;

public class RecipeConsoleStepsDefinition {

    private final RecipeConsolePage recipeConsolePage;
    private Recipe currentRecipe;
    private final List<Recipe> recipes;
    private final Report report;
    private final Analytics analytics;
    private final ReportsPage reportPage;

    public RecipeConsoleStepsDefinition(RecipeConsolePage recipeConsolePage, Report report, Analytics analytics,
            Recipe currentRecipe, ReportsPage reportPage) {
        this.recipeConsolePage = recipeConsolePage;
        this.recipes = new ArrayList<>();
        this.report = report;
        this.analytics = analytics;
        this.report.setRecipes(this.recipes);
        this.analytics.setRecipes(this.recipes);
        this.currentRecipe = currentRecipe;
        this.reportPage = reportPage;
    }

    @Given("I expand recipe console in pnid")
    public void iGotoRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
        Selenide.sleep(2000);
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

    @Then("I verify all the recipes are displayed")
    public void iVerifyLoadRecipePage() {
        recipeConsolePage.verifyLoadRecipePage();
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

    @When("I start and pause recipe execution during {int} seconds")
    public void iStartAndPauseRecipeExecution(int seconds) {
        iStartAndPauseRecipeExecution(seconds, null, null);
    }

    public void iStartAndPauseRecipeExecution(int seconds, String batchId, String productId) {
        generateRecipeValues(batchId, productId);
        String runId = recipeConsolePage.startAndPauseRecipe(this.currentRecipe, seconds);
        currentRecipe.setRunId(runId);
        recipes.add(currentRecipe);
    }

    @When("I load recipe {string} and pause it during {int} seconds")
    public void iStartAndPauseRecipeExecution(String recipe, int seconds) {
        iGotoRecipeConsole();
        iLoadRecipeAndIPauseIt(recipe, seconds);
    }

    private void iLoadRecipeAndIPauseIt(String recipe, int seconds) {
        iLoadRecipe(recipe);
        iStartAndPauseRecipeExecution(seconds);
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
        this.currentRecipe.setSteps(recipeConsolePage.loadedRecipeStepCount());
        recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getBeforeComments());
    }

    @When("I rerun recipe execution and timer starts from zero")
    public void iRerunRecipeExecution() throws ParseException {
        generateRecipeValues(null, null);
        recipeConsolePage.reRunRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getBeforeComments());
    }

    @When("I provide special chars in pre run comments")
    public void iStartRecipeExecutionWithSpecialChars() {
        generateRecipeValues(null, null);
        recipeConsolePage.startRecipeWithErrors(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                "$%^%(&^&");
    }

    @Then("I see the error message as {string}")
    public void iSeeErrorMessage(String message) {
        recipeConsolePage.verifyCommentErrorMessage(message);
        String runId = recipeConsolePage.startRecipe(this.currentRecipe);
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

    @And("I wait the end of the execution of the recipe")
    public void iWaitTheEndOfTheExecutionOfTheRecipe() {
        recipeConsolePage.isExecuted();
    }

    @When("I click on pause button")
    public void iClickOnPauseButton() {
        recipeConsolePage.clickPauseButton();
    }

    @Then("control should be on resume button")
    public void ctrlOnResumeButton() {
        recipeConsolePage.verifyResumeButton();
    }

    @Then("control should be on pause button")
    public void ctrlOnPauseButton() {
        recipeConsolePage.verifyPauseButton();
    }

    @Then("control should be on rerun button")
    public void ctrlOnReRunButton() {
        recipeConsolePage.verifyReRunButton();
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
        Selenide.sleep(seconds * 1000L);
    }

    @Then("I see the system on hold")
    public void iSeeTheSystemOnHold() {
        recipeConsolePage.seeSystemOnRestart();
    }

    @Then("I see the system is restarted")
    public void iSeeTheSystemRestarted() {
        recipeConsolePage.seeSystemRestarted();
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
        Assert.assertTrue(
                restartOrHoldContent.equals(expectedRestartText) || restartOrHoldContent.equals(expectedHoldText));
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
    public void recipeExecIsPaused() throws ParseException {
        recipeConsolePage.recipeIsPaused();
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
    public void iGoToManualOperationTab() {
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

    @Then("I see the system on restart")
    public void iSeeTheSystemOnRestart() {
        recipeConsolePage.seeSystemOnRestart();
    }

    @Then("I should see the recipe run {string}")
    public void iVerifyRecipeAbort(String status) {
        if (status.equalsIgnoreCase("Aborted")) {
            Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
            recipeConsolePage.clickOnOk();
        } else if (status.equalsIgnoreCase("Completed")) {
            Assert.assertEquals("Completed", this.recipeConsolePage.getExecutionStatusText());
            recipeConsolePage.clickOnOk();
        }
    }

    @Then("I should see error message about recipe step")
    public void errorMessageOfJumpStep() {
        recipeConsolePage.jumpStepErrorMessage(this.currentRecipe.getSteps());
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
        recipeConsolePage.manualOperationTab(status);
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
        recipeConsolePage.startRerunRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getBeforeComments());
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
        if (status.equalsIgnoreCase("Manual operation")) {
            recipeConsolePage.manualOperation("enabled");
        } else if (status.equalsIgnoreCase("Recipe Run")) {
            recipeConsolePage.recipeRun("enabled");
        }
    }

    @Then("I should see start button is displayed")
    public void iSeeStartButton() {
        recipeConsolePage.iValidateStart();
    }

    @Then("I should not see special characters not allowed")
    public void iShouldNotSeeSpecialCharactersNotAllowed() {
        recipeConsolePage.iValidationPreRun();
    }

    @When("I resume and verify recipe execution is resumed")
    public void resumeStatus() {
        recipeConsolePage.clickResumeButton();
        ctrlOnPauseButton();
    }

    // we enchance the code on merging (Run, Rerun & Start)
    @And("I start Manual run")
    public void manualRun() throws InterruptedException {
        generateRecipeValues(null, null);
        String runId = recipeConsolePage.manualRunStart(this.currentRecipe.getProductId(),
                this.currentRecipe.getBatchId(), this.currentRecipe.getBeforeComments());
        currentRecipe.setRunId(runId);
        recipes.add(currentRecipe);
    }

    @Then("I validate the timer, stop button, run details")
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
    public void timerIncrementCheck() {
        recipeConsolePage.incrementTimer();
    }

    @When("I Stop the RUN")
    public void stopRun() {
        recipeConsolePage.stopButton();
    }

    @Then("I validate the date formats in Post run window and enter comments")
    public void validatePostRun() {
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

    @Given("I click on load recipe")
    public void clickOnLoadRecipe() {
        recipeConsolePage.clickOnLoadRecipe();
    }

    @Then("I should not see unapproved recipe")
    public void iVerifyUnApprovedRecipe() {
        recipeConsolePage.verifyApprovedRecipe();
        recipeConsolePage.clickOnClose();
    }

    @Then("I should see recipe name and recipe steps details")
    public void iVerifyRecipeNameAndRecipeSteps() {
        recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId());
    }

    @When("I click on start button")
    public void iSelectStartButton() {
        recipeConsolePage.start_button();
    }

    @Then("I close post run window and select re run option")
    public void iSeePreRunWindowPopUp() {
        recipeConsolePage.clickOnOk();
        recipeConsolePage.reRun();
        recipeConsolePage.preRunWindow_Popup();
    }

    @When("I click ok button")
    public void iClickOkButton() {
        recipeConsolePage.okButton();
    }

    @Then("I should see {string} message")
    public void iSeeMessage(String message) {
        recipeConsolePage.validateHighlightedMsg(message);
    }

    @When("I enter existing value in RUNID")
    public void iEnterExistingValueInRunId() {
        recipeConsolePage.runIdManual(this.currentRecipe.getRunId());
    }

    @Then("I should see message {string}")
    public void iShouldSeeMessage(String message) {
        recipeConsolePage.validateMsg(message);
    }

    @And("I Verify manual run status in recipe console")
    public void iVerifyRecipeRunStatus() {
        generateRandomRecipeValues();
        recipeConsolePage.manualValidation(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(),
                this.currentRecipe.getBatchId(), this.currentRecipe.getProductId(),
                this.currentRecipe.getBeforeComments());
    }

    @And("I start manual recipe execution")
    public void iStartManualRecipeExecution() {
        generateRandomRecipeValues();
        recipeConsolePage.manualValidation(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(),
                this.currentRecipe.getBatchId(), this.currentRecipe.getProductId(),
                this.currentRecipe.getBeforeComments());
        recipeConsolePage.okButton();
        recipeConsolePage.stopBtn();
        recipeConsolePage.validateYesBtn();
    }

    @And("I see Recipe should be executed")
    public void iSeeRecipeShouldBeExecuted() {
        Assert.assertTrue(recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId()));
    }

    @When("I enter special characters {string} in comments section")
    public void iValidateSpecialChar(String specialChar) {
        generateRandomRecipeValues();
        recipeConsolePage.iValidateSpecialCharManual(this.currentRecipe.getManualOperationName(),
                this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(), this.currentRecipe.getProductId(),
                specialChar);
    }

    @Then("I should see special characters not allowed")
    public void iShouldNotSeeSpecialCharacters() {
        recipeConsolePage.iVerifySpecialCharacterMsg();
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
    public void iSelectRunRecipe() {
        recipeConsolePage.run_Btn();
    }

    @When("I expand recipe console")
    public void iExpandRecipeConsole() {
        recipeConsolePage.gotoRecipeConsole();
    }

    @And("I verify the recipe execution details in console View.")
    public void iVerifyRecipeExecution() {
        recipeConsolePage.expandRecipeConsole();
        recipeConsolePage.verifyRecipeDetails(this.currentRecipe.getBatchId());
    }

    @And("I verify the Batch ID suggestion with unique Value")
    public void iVerifyBatchIdWithUniqueValue() {
        generateRandomRecipeValues();
        recipeConsolePage.uniqBatchId(this.currentRecipe.getBatchId());
    }

    @When("I Select Process Hold")
    public void iSelectedProcessHold() {
        recipeConsolePage.processHold();
    }

    @And("I verify the Process hold Dialog box , buttons")
    public void iVerifyProcessHoldDialogBox() {
        recipeConsolePage.iVerifyDialogBox();
    }

    @And("I Select Yes button")
    public void iSelectYesButton() {
        recipeConsolePage.validateYesBtn();
    }

    @And("I Select confirm button")
    public void iSelectConfirmButton() {
        recipeConsolePage.validateConfirmBtn();
    }

    @And("I validate close,No button functionality")
    public void iVerifyCloseAndNoButtonFunctionality() {
        recipeConsolePage.validateNoBtn();
    }

    @And("I validate close,cancel button functionality")
    public void iVerifyCloseAndCancelButtonFunctionality() {
        recipeConsolePage.validateCancelBtn();
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
    public void iVerifyProcessRestartDialogBox() {
        recipeConsolePage.iVerifyProcessRestartPopup();
    }

    @Then("I should see change of Process restating to Process hold")
    public void iSeeProcessRestartingToProcessHold() {
        recipeConsolePage.iVerifyProcessRestartToProcessHold();
    }

    private void generateRandomRecipeValues() {
        this.currentRecipe.setProductId(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setBatchId(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setBeforeComments(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setAfterComments(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setManualOperationName(RandomStringUtils.randomAlphabetic(10));
        this.currentRecipe.setRunId(RandomStringUtils.randomAlphabetic(10));
    }

    @And("I verify all mandatory fields has asterisk mark {string}")
    public void iVerifyAllMandatoryFieldsHasAsteriskMark(String mark) {
        recipeConsolePage.reRun();
        recipeConsolePage.iVerifyAsteriskMark(mark);
    }

    @When("I enter special characters {string} in run comments section")
    public void iValidateRunRecipeComments(String specialChar) {
        generateRandomRecipeValues();
        recipeConsolePage.iValidateSpecialCharRun(this.currentRecipe.getRunId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getProductId(), specialChar);
        recipeConsolePage.manualValidation(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(),
                this.currentRecipe.getBatchId(), this.currentRecipe.getProductId(),
                this.currentRecipe.getBeforeComments());
        recipeConsolePage.okButton();
        recipeConsolePage.stopBtn();
        recipeConsolePage.validateYesBtn();
    }

    @And("I wait for {int} sec and stop the run")
    public void iWaitAndStopManualOperation(int seconds) {
        recipeConsolePage.startAndWaitManualOperation(seconds);
        recipeConsolePage.clickOnOk();
    }

    @When("I load recipe {string} and select run button")
    public void iLoadRecipeAndRunTheRecipe(String recipe, int seconds) {
        iGotoRecipeConsole();
        iLoadRecipeAndIStartIt(recipe, seconds);
    }

    @Then("I should see pre run window")
    public void iSeePreRunWindow() {
        recipeConsolePage.run_Btn();
        recipeConsolePage.preRunWindow_Popup();
    }

    @When("I clear and try to enter lengthy RUN ID, BatchID")
    public void iEnterLengthyRunIdAndBatchID() {
        this.currentRecipe.setRunId(RandomStringUtils.randomAlphabetic(50));
        recipeConsolePage.runIdManual(this.currentRecipe.getRunId());
        this.currentRecipe.setBatchId(RandomStringUtils.randomAlphabetic(45));
        recipeConsolePage.uniqBatchId(this.currentRecipe.getBatchId());
    }

    @And("provide remaining mandatory data to select OK button")
    public void provideRemainingData() {
        generateRandomRecipeValues();
        recipeConsolePage.iProvideData(this.currentRecipe.getProductId(), this.currentRecipe.getBeforeComments());
    }

    @Then("I verify the recipe name is trimmed on recipe console UI")
    public void iVerifyUiFieldsOfManualOperations() {
        recipeConsolePage.iVerifyRecipeName();
        recipeConsolePage.iVerifyRecipeNameDisplayedOrTrimmed("Trimmed");
        recipeConsolePage.iVerifyConditionalStatement();
    }

    @And("I verify the recipe lengthy step is trimmed")
    public void iCheckRecipeStepsTrimmed() {
        generateRecipeValues(null, null);
        String runId = recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getBeforeComments());
        this.currentRecipe.setRunId(runId);
    }

    @Then("I verify mouse hover on step displays tool tip with full step details")
    public void iVerifyMouseHoverStepDetails() {
        recipeConsolePage.iCheckStepDetailsWithMouseHover();
        recipeConsolePage.clickOnAbortButton("Run is aborted.");
    }

    @And("I should see recipe execution started successfully")
    public void iSeeRecipeExecution() {
        recipeConsolePage.verifyAbortButton();
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

    @Then("Verify the recipe console extended view UI components")
    public void iVerifyUIComponents() {
        recipeConsolePage.iVerifyRecipeConsoleElement();
        recipeConsolePage.checkButton();
    }

    @Given("I load the recipe {string}")
    public void iLoadTheRecipe(String recipe) {
        this.currentRecipe = new Recipe();
        this.currentRecipe.setRecipeName(recipe);
        recipeConsolePage.iLoadRecipeLink(recipe);
    }

    @And("I enter manual operation name more than 30 char and Tab out")
    public void iEnterLengthyManualOperationName() {
        recipeConsolePage.iCheckLengthyCharacter();
    }

    @And("Verify the warning message {string}")
    public void iVerifyErrorMessage(String name) {
        recipeConsolePage.iCheckErrorMessage(name);
    }

    @And("I start the recipe run with lengthy text on RUNID,BATCHID,PRODUCTID")
    public void iEnterLengthyText() {
        generateRandomRecipeValues();
        recipeConsolePage.iEnterLengthyChar(this.currentRecipe.getManualOperationName(), this.currentRecipe.getRunId(),
                this.currentRecipe.getBatchId(), this.currentRecipe.getProductId());
    }

    @And("I validate all above text value trimmed on recipe console UI")
    public void iVerifyTextValue() {
        recipeConsolePage.iVerifyDisplayedOrTrimmed("Trimmed");
    }

    @And("I mouse hover to see full text displayed on tooltip")
    public void iVerifyTextDisplayedWithUsingMouseHover() {
        recipeConsolePage.iVerifyDisplayedOrTrimmed("Display");
    }

    @And("I stop the run execution")
    public void iStopRunExecution() {
        recipeConsolePage.stopBtn();
        recipeConsolePage.validateYesBtn();
    }

    @And("I verify the text value trimmed on post run window")
    public void iVerifyTextValueTrimmedOnPostRunWindow() {
        recipeConsolePage.iVerifyPostRunWindowValues("Trimmed");
    }

    @Then("I verify recipe console expand is disabled")
    public void iVerifyRecipeConsoleExpandAndCollapse() {
        recipeConsolePage.collapseRecipeConsoleNotDisplay();
    }

    @And("I mouse hover to see full text displayed  on tooltip")
    public void iVerifyTextWithMouseHover() {
        recipeConsolePage.iVerifyPostRunWindowValues("Display");
        recipeConsolePage.okButton();
    }

    @And("I verify the recipe name displayed on load recipe list")
    public void iVerifyTheRecipeNameDisplayed() {
        recipeConsolePage.iVerifyRecipeNameDisplayedOrTrimmed("Display");
    }

    @And("I check manual run report")
    public void manualRunReportCheck() throws Exception {
        Map<String, String> list = new HashMap<>();
        list.put("Unit Operation name", "IVI");
        list.put("Batch ID", this.currentRecipe.getBatchId());
        list.put("ProductID", this.currentRecipe.getProductId());
        list.put("RunID", this.currentRecipe.getRunId());
        list.put("Start Date", this.currentRecipe.getStartDate());
        list.put("End Date", this.currentRecipe.getEndDate());
        list.put("Pre-run Comment", this.currentRecipe.getBeforeComments());
        list.put("Post-run Comment", this.currentRecipe.getAfterComments());
        list.put("Run Status", this.currentRecipe.getStatus());
        this.report.validateManualRunSummary(reportPage.getPdfUrl(), list);
        this.report.checkEventTable(reportPage.getPdfUrl());
        this.report.checkAuditTable(reportPage.getPdfUrl());
        this.report.checkProcessSystemAlarm(reportPage.getPdfUrl(), "Process Alarm");
        this.report.checkProcessSystemAlarm(reportPage.getPdfUrl(), "System Alarm");
    }

    public void runRecipe(String recipeName, String stepNo) {
        recipeConsolePage.gotoRecipeConsole();
        Selenide.sleep(2000);
        this.currentRecipe = new Recipe();
        this.currentRecipe.setRecipeName(recipeName);
        recipeConsolePage.loadRecipe(recipeName);
        generateRecipeValues(null, null);
        this.currentRecipe.setSteps(recipeConsolePage.loadedRecipeStepCount());
        recipeConsolePage.startRecipe(this.currentRecipe.getProductId(), this.currentRecipe.getBatchId(),
                this.currentRecipe.getBeforeComments());
        iClickOnPauseButton();
        recipeConsolePage.verifyResumeButton();
        recipeConsolePage.clickResumeButton();
        recipeConsolePage.verifyPauseButton();
        recipeConsolePage.clickOnJumpToStep(stepNo);
        recipeConsolePage.clickOnAbortButton(this.currentRecipe.getAfterComments());
        Assert.assertEquals("Aborted", this.recipeConsolePage.getExecutionStatusText());
        recipeConsolePage.clickOnOk();
        recipeConsolePage.verifyReRunButton();
    }

}
