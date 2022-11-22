package cucumber.steps;

import dataobjects.Recipe;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import com.codeborne.selenide.Selenide;

import pageobjects.pages.RecipeConsolePage;
import pageobjects.utility.SelenideHelper;

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
        recipeConsolePage.clickOnAbortButton(this.recipe.getAfterComments());
        recipeConsolePage.clickOnOk();
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

    @And("I clear the recipe")
    public void clearRecipePanel() {
        recipeConsolePage.clearRecipe();
    }
    @Then("I should see Error message")
    public void errorMessageOfJumpStep() throws InterruptedException {
    	recipeConsolePage.jumpStepErrorMessage();
    	recipeConsolePage.clickOnAbortButton(this.recipe.getAfterComments());
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
    @And("I pause recipe and verify recipe paused")
    public void pauseButton() {
    	recipeConsolePage.clickPauseButton();
    	ctrlOnResumeButton();
    }
    @When("I re-run the recipe")
    public void reRunRecipe() {
    	recipeConsolePage.reRun();
    	generateRandomRecipeValues();
    	recipeConsolePage.startRerunRecipe(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments());
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
		   if(true) {
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
	   generateRandomRecipeValues();
   	   recipeConsolePage.manualRunStart(this.recipe.getProductId(), this.recipe.getBatchId(), this.recipe.getBeforeComments());
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
   public void validatePostRun() {
	   recipeConsolePage.verifyPostRunDate();
   }
   @And("I wait for 1 min for the post run window to auto closed")
   public void verifyAutoClosePostRun() {
	   recipeConsolePage.autoClosePostRun();
   }
   @And("I validate the Start button is {string} and {string}")
   public void afterPostRunAutoClose(String status1, String status2) {
	   recipeConsolePage.validateStartButtonNotSelect(status1,status2);
   }

}
