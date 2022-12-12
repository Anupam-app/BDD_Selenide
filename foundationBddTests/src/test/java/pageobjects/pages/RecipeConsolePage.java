package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.Wait;
import dataobjects.Recipe;
import org.apache.commons.lang3.RandomStringUtils;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import dataobjects.Recipe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class RecipeConsolePage {

    private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
    private final String XPATH_RESTART_OR_HOLD_BUTTON = "//span[@class='MuiButton-label']";
    private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
    private final String XPATH_RECIPE_LOADED_BEFORE = "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";
    private final String XPATH_CTRL_ICONS = "//img[contains(@src,'%s')]";
    private final String XPATH_TEXTS = "//p[text()='%s']";
    private final String recipeListTableValues = "//tbody/tr[%d]/td[%d]/label";

    private final SelenideElement loadRecipeText = $(By.xpath("//p[contains(text(),'Load Recipe')]"));
    private final SelenideElement clearRecipeText = $(By.xpath("//p[contains(text(),'Clear Panel')]"));
    private final SelenideElement runCommentErrorMsg = $(By.xpath("//span[(text()='Special characters are not allowed for Comments')]"));
    private final String errorMessage = "//h6[contains(text(),'invalid step number enter:1-%s')]";
    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
	private final SelenideElement postRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
	private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));
	private final String stepNo = "//p[text()='%S']";
	
    private final SelenideElement expandIcon = $(By.xpath("//img[contains(@class,'jss') and contains(@src,'Tab')]"));
    private final SelenideElement collapseIcon = $(By.xpath("//img[@class='jss8']"));
    private final SelenideElement manualOperations = $(By.xpath("//span[text()='MANUAL OPERATION']"));
    private final SelenideElement timerValue = $(By.xpath("//div[text()='00:00:00:00']"));
    private final SelenideElement runIcon = $(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement abortIcon = $(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement rerunIcon = $(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement pauseIcon = $(By.xpath("//img[contains(@src,'Group')]"));
    private final SelenideElement resumeIcon = $(By.xpath("//img[contains(@src,'RESUME')]"));
    private final SelenideElement jumpStepIcon = $(By.xpath("//img[contains(@src,'JUMP_STEP')]"));
    private final SelenideElement pauseTimerValue = $(By.xpath("//p[contains(text(),'0:00')]"));
    private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));
    private final SelenideElement recipeButton = $(By.xpath("//span[contains(text(),'RECIPE RUN')]"));
	private final SelenideElement recipeButtonSelected = $(By.xpath("//button[@class ='MuiButtonBase-root MuiButton-root MuiButton-outlined jss24 MuiButton-outlinedSecondary']"));
	private final SelenideElement pauseButton = $(By.xpath("//img[@src='/useradminportal/static/media/Group 8.59d83e21.svg']"));
    
    private final SelenideElement restartButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "RESTART")));
    private final SelenideElement confirmButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "Confirm")));
    private final SelenideElement reEstablishStateButton = $(By.xpath("(//*[contains(@class, 'PrivateSwitchBase-input')])[1]"));
    private final SelenideElement yesButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "Yes")));
    private final SelenideElement holdButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "HOLD")));
    private final SelenideElement loadButton = $(By.xpath("//span[contains(text(),'Load')]"));
    private final SelenideElement okStepButton = $(By.xpath("//span[contains(text(),'Ok')]"));
    private final SelenideElement clickYesButton = $(By.xpath("//span[contains(text(),'Yes')]"));
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));

    private final SelenideElement runIdTextbox = $(By.xpath("//input[@name='runId']"));
    private final SelenideElement productIdTextbox = $(By.xpath("//input[@name='productId']"));
    private final SelenideElement batchIdTextbox = $(By.xpath("(//*[contains(@class,'autocompletediv')]//input)[1]"));
    private final SelenideElement startDate = $(By.xpath("//span[@id='startDate_Id']"));
    private final SelenideElement endDate = $(By.xpath("//span[@id='endDate_Id']"));
    private final SelenideElement machineName = $(By.xpath("//span[@id='machine_Id']/label"));

    private final SelenideElement clearRecipeButton = $(By.xpath("//*[contains(@class,'MuiTypography-root') and text()='Clear Panel']"));
    private ElementsCollection recipeStepCount = $$(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column']/div/div[@class='MuiGrid-root MuiGrid-container']"));
	private final SelenideElement closeJumpStep = $(By.xpath("//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAFKADAAQAAAABAAAAEwAAAAAA/SztAAABDUlEQVQ4Ea3UUQuCMBDA8UulqKeIkCCQoO//kYIIepGejSKt/oOTabqd0L3opvdrN6/N3t+QbzweT8myVNI0ZTgp6rqW16uWxWIuGZlgp9NZkiSRotjLarU0g1V1l8vlKk3TyPF4kIRMVgbGJA95yRI+Rr5zSKRMVjYF7WPk47gVglKmFR3CdJta0IqGMIwOGENjGPkzbRsGfvST83wrZXlzHy7UDaMguI/qj4Uw3vkpWRO5stGszA/G+gH8eb0PgqyQMv1gzPxYjIJ+uZS52+WmPh0E+xj9udmsTX36Aw5humdcY83fAUOY7lkMbUELZkEdyHmmR1Csz0IojgM5HDm6rNgYitP+U/51Yn8AzV4maDdMLYMAAAAASUVORK5CYII=']"));    
	private final SelenideElement manualStartButton = $(By.xpath("//img[contains(@src,'START_btn')]"));
	private final SelenideElement manualOperationName = $(By.xpath("//input[@name ='recipeName']"));
	private final SelenideElement manualStopButton = $(By.xpath("//img[contains(@src,'End_btn Copy-End_btn')]"));
	private final SelenideElement matchId = $(By.xpath("(//label[@id='trimString'])[1]"));
	private final SelenideElement batchId = $(By.xpath("(//label[@id='trimString'])[2]"));
	private final SelenideElement runId = $(By.xpath("(//label[@id='trimString'])[3]"));
	private final SelenideElement timeValidate = $(By.xpath("//div[@id='timerCycle']")); 
	private final SelenideElement secondValidate = $(By.xpath("//div[@id='timerCycle']//span[4]"));
	private final SelenideElement minuteValidate = $(By.xpath("//div[@id='timerCycle']//span[3]"));
	private final SelenideElement closeButtonOfStop = $(By.xpath("//span[contains(text(),'Yes')]"));
	private final SelenideElement postRunWindow = $(By.xpath("//p[contains(text(),'Post-Run Record')]"));
	private final SelenideElement manualOperationButton = $(By.xpath("//span[contains(text(),'MANUAL OPERATION')]"));
	private final SelenideElement manualOperationSelected = $(By.xpath("//button[contains(@class, 'MuiButton-outlinedPrimary')]//span[contains(text(),'MANUAL OPERATION')]"));
	private final ElementsCollection recipeListTable = $$(By.xpath("//tbody/tr"));
	
    private Recipe recipe;

    public RecipeConsolePage(Recipe recipe) {

        this.recipe = recipe;
    }

    public void holdAndRestart() {
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
        holdSystem();
        restartSystem();
    }

    public void restartSystem() {
        SelenideHelper.commonWaiter(restartButton, visible).click();
        SelenideHelper.commonWaiter(reEstablishStateButton, enabled).click();
        SelenideHelper.commonWaiter(confirmButton, visible).click();
        holdButton.waitUntil(visible, 10000);
    }

    public void gotoRecipeConsole() {
        if (expandIcon.isDisplayed()) {
            SelenideHelper.commonWaiter(expandIcon, visible).click();
        }
    }

    public void collapseRecipeConsole() {
        SelenideHelper.commonWaiter(collapseIcon, visible).click();
    }

    public void loadRecipe(String recipeName) {
    	if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
		if(abortIcon.isDisplayed()) {
        	abortIcon.click();
        	clickYesButton.waitUntil(Condition.visible, 1000).click();
        	okButton.waitUntil(Condition.visible, 5001).click(); 	
        }
        if ($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
        }
        $(By.xpath(String.format(XPATH_TEXTS, "Load Recipe"))).click();
        loadButton.waitUntil(Condition.visible, 20000l);
        $(By.xpath(String.format(XPATH_LOAD_RECIPE, recipeName))).click();
        loadButton.click();
    }
    
    public void openLoadRecipePage() {
    	if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
		if(abortIcon.isDisplayed()) {
        	abortIcon.click();
        	clickYesButton.waitUntil(Condition.visible, 1000).click();
        	okButton.waitUntil(Condition.visible, 5001).click(); 	
        }
        if ($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
        }
        $(By.xpath(String.format(XPATH_TEXTS, "Load Recipe"))).click();
    }
    
    public void verifyTooltipLoadRecipePage() {
		
    	//Check the values for RecipeName Column
    	for(int i=1;i<=recipeListTable.size();i++) {
    		Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i,1))).getAttribute("title").matches("^[a-zA-Z0-9]*$"));
    	}
    	//Check the values for status Column
    	for(int i=1;i<recipeListTable.size();i++) {
    		Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i,2))).getAttribute("title").equals("Approved-Active"));
    	}
    	//Check the values for date Column
    	for(int i=1;i<recipeListTable.size();i++) {
    		Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i,3))).getAttribute("title").matches(("([a-zA-Z0-9]{3}) ([0-9]{2}),([0-9]{4})")));
    	}
    	//Check the values for created By Column
    	for(int i=1;i<recipeListTable.size();i++) {
    		Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i,4))).getAttribute("title").equals("Bio4CAdmin"));
    	}
    	
    }
    
    public void holdSystem() {
		SelenideHelper.commonWaiter(holdButton, visible).click();
		SelenideHelper.commonWaiter(yesButton, visible).click();
	}

    public String startAndWaitRecipe(Recipe recipe, int seconds) {

        String runId;
        String[] dateParts = null;
        String[] dateparts1 = null;

        runIcon.waitUntil(Condition.visible, 20000l);
        runIcon.click();
        runId = runIdTextbox.getValue();
        productIdTextbox.setValue(recipe.getProductId());
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(recipe.getBatchId());
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(recipe.getBeforeComments());
        commonWaiter(okButton, visible).click();
        abortIcon.waitUntil(Condition.visible, 5000l);
        abortIcon.waitUntil(Condition.not(Condition.visible), seconds * 2000l);
        commonWaiter(startDate, visible);
        recipe.setStartDate(startDate.getText());
        recipe.setEndDate(endDate.getText());
        recipe.setMachineName(machineName.getText());
        recipe.setStatus(executionStatusText.getText());
        preRunCommentsText.sendKeys(recipe.getAfterComments());
        okButton.click();

        return runId;
    }


    public String startAndWaitRecipe(String productId, String batchId, String beforeComments, String afterComments,
                                     int seconds) {

        String runId;

        runIcon.waitUntil(Condition.visible, 20000l);
        runIcon.click();
        runId = runIdTextbox.getValue();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);
        okButton.click();
        abortIcon.waitUntil(Condition.visible, 5000l);
        abortIcon.waitUntil(Condition.not(Condition.visible), seconds * 1000l);
        commonWaiter(startDate, visible);
        String startDate1 = startDate.getText();
        String endDate1 = endDate.getText();
        String[] dateParts = startDate1.split(" ");
        String[] dateparts1 = endDate1.split(" ");
        preRunCommentsText.sendKeys(afterComments);
        this.recipe.setStartDate(dateParts[0]);
        this.recipe.setEndDate(dateparts1[0]);
        this.recipe.setBeforeComments(beforeComments);
        this.recipe.setAfterComments(afterComments);
        this.recipe.setMachineName(machineName.getText());
        okButton.click();

        return runId;
    }

    public void startRecipe(String productId, String batchId, String beforeComments) {
        runIcon.waitUntil(Condition.visible, 20000l);
        runIcon.click();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);
        okButton.click();
        Selenide.sleep(2000);
    }
    
    public void reRunRecipe(String productId, String batchId, String beforeComments) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("DD:HH:MM:SS");
        String timeStart = pauseTimerValue.getText();
        Date startTime = formatter.parse(timeStart);
        rerunIcon.waitUntil(Condition.visible, 20000l);
        rerunIcon.click();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);
        okButton.click();
        Selenide.sleep(3000);
        String timeEnd = pauseTimerValue.getText();
        Date endTime = formatter.parse(timeEnd);
        long timeDifference = (endTime).getTime() - (startTime).getTime();
        if (timeDifference < 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }
    
    
    public void verifyCommentErrorMessage(String message) {
    	$(By.xpath("//label[text()='Comments']")).click();
    	Assert.assertTrue((commonWaiter(runCommentErrorMsg, visible)).getText().equalsIgnoreCase(message));
    }

    public void isExecuted() {
		rerunIcon.waitUntil(Condition.visible, 5000l);
	}
	public void isExecuted(int seconds) {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, seconds * 1000l);
	}

    public void clickPauseButton() {
        pauseIcon.click();
    }

    public void clickResumeButton() {
        resumeIcon.waitUntil(Condition.visible, 5000l).click();
    }

    public boolean verifyResumeButton() {
        return (resumeIcon.waitUntil(Condition.visible, 50001).isDisplayed());
    }

    public boolean verifyPauseButton() {
        return (pauseIcon.waitUntil(Condition.visible, 50001).isDisplayed());
    }

    public boolean verifyReRunButton() {
        return (rerunIcon.waitUntil(Condition.visible, 50001).isDisplayed());
    }

    public void clickOnJumpToStep(String stepNumber) {
        Selenide.sleep(2000);
        jumpStepIcon.waitUntil(Condition.visible, 4000l, 50l).click();
        inputStepNumber.waitUntil(Condition.visible, 4000l, 50l);
        Selenide.sleep(1000);
        inputStepNumber.sendKeys(stepNumber);
        okStepButton.waitUntil(Condition.visible, 4000l, 500).click();
        abortIcon.waitUntil(visible, 4000l, 500);
        Selenide.sleep(2000);
        SelenideHelper.takePicture();
    }

    public void clickOnAbortButton(String afterComments) {
        abortIcon.waitUntil(Condition.visible, 6000l).click();
        clickYesButton.waitUntil(Condition.visible, 5000l).click();
        SelenideHelper.commonWaiter(preRunCommentsText, visible).sendKeys(afterComments);
    }

    public void seeSystemOnHold() {
        SelenideHelper.commonWaiter(holdButton, visible);
    }

    public String getExecutionStatusText() {
        return executionStatusText.getText();
    }

    public void clickOnOk() {
        okButton.waitUntil(Condition.visible, 5000l).click();
    }

    public void cleanLastRecipeDisplay() {
        if (okButton.isDisplayed()) {
            okButton.click();
        }
    }

    public boolean isRunBefore(String recipeName) {
        return $(By.xpath(String.format(XPATH_RECIPE_LOADED_BEFORE, recipeName, recipeName))).isDisplayed();
    }

    public String seeRestartOrHoldContent() {
        return commonWaiter($(By.xpath(XPATH_RESTART_OR_HOLD_BUTTON)), visible).text();
    }

    public void clearRecipe() {
        SelenideHelper.commonWaiter(clearRecipeButton, visible).click();
    }

    public void recipeisPaused() throws ParseException {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).shouldNotBe(selected);
        SimpleDateFormat formatter = new SimpleDateFormat("DD:HH:MM:SS");
        String timeStart = pauseTimerValue.getText();
        Selenide.sleep(2000);
        String timeEnd = pauseTimerValue.getText();
        Date startTime = formatter.parse(timeStart);
        Date endTime = formatter.parse(timeEnd);
        long timeDifference = (endTime).getTime() - (startTime).getTime();
        if (timeDifference > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    public void hold() {
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
        holdSystem();
    }

    public void clearPanelAndRunDisabled() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).shouldNotBe(selected);
        $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).shouldNotBe(selected);
    }

    public void gotoManualOperations() {
        SelenideHelper.commonWaiter(manualOperations, visible).click();
    }

    public void verifyManualRunOptions() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "START"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "Batch ID"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "Run ID"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "DHMS"))).shouldBe(visible);
        timerValue.shouldBe(visible);

    }
	
	public void verifyJumpStep() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).shouldNotBe(selected);
	}

    public void verifyRecipeRunOptions() {
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
        if ($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
        }
        $(By.xpath(String.format(XPATH_TEXTS, "Load Recipe"))).shouldBe(visible);
        holdButton.shouldBe(visible);
    }
	
	public void jumpStepErrorMessage(){
		var stepCount = recipeStepCount.size();
		$(By.xpath(String.format(errorMessage,stepCount))).isDisplayed();
		closeJumpStep.click();
	}
	public void verifyStep(String stepNumber) {
		clickOnJumpToStep(stepNumber);
		var reviewStatus = $(By.xpath(String.format(stepNo,stepNumber)));
		SelenideHelper.commonWaiter(reviewStatus, visible);

	}
	public void manualOperation(String status) {

		if(status.equalsIgnoreCase("enabled")) {
			manualOperationButton.waitUntil(visible, 50001).click();
			manualOperationSelected.shouldBe(visible);
		}
		else if(status.equalsIgnoreCase("disabled")) {
			commonWaiter(manualOperationButton, visible);
			manualOperationButton.shouldNotBe(selected);
		}
	}
	public void recipeRun() {
		recipeButton.waitUntil(visible,50001).click();
		recipeButtonSelected.shouldBe(visible);
	}
	public void pauseButton() {
		pauseButton.waitUntil(visible, 50001);
	}
	public void reRun() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 5000l).click();
	}
	public void processHold() {
		$(By.xpath(String.format(XPATH_PNID_BUTTON, "PROCESS HOLD"))).click();
	}
	//Need to club with RecipeRun method(visible and disable), currently overloading
	public void recipeRun(String status) {
		if(status.equalsIgnoreCase("disabled")) {
			recipeButton.shouldNotBe(selected);
		}
		else if(status.equalsIgnoreCase("enabled")) {
			recipeButton.waitUntil(visible, 50001).click();
			recipeButton.shouldBe(visible);
		}
	}
	public void startRerunRecipe(String productId, String batchId, String beforeComments) {
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(beforeComments);
		okButton.click();
	}
	public void manualRunStart(String productId, String batchId, String beforeComments) {
		if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
		if(manualStopButton.isDisplayed()) {
			manualStopButton.click();
			closeButtonOfStop.click();
			okButton.click();
		}
		commonWaiter(manualStartButton, appear);
		manualStartButton.click();
		this.recipe.setMachineName(RandomStringUtils.randomAlphabetic(5));
		manualOperationName.sendKeys(this.recipe.getMachineName());
		this.recipe.setRunId(RandomStringUtils.randomAlphabetic(5));
		runIdTextbox.sendKeys(this.recipe.getRunId());
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(beforeComments);
		okButton.click();
	}
	public boolean verifyStopButton() {
		return(manualStopButton.waitUntil(Condition.visible,50001).isDisplayed());
	}
	public void validationOfRunDetails() {
		commonWaiter(manualStopButton,appear); 
		String actualRunId = matchId.getText();
		Assert.assertEquals(actualRunId.toLowerCase(), this.recipe.getMachineName().toLowerCase());
		String actualBatchId = batchId.getText();
		Assert.assertEquals(actualBatchId.toLowerCase(), this.recipe.getBatchId().toLowerCase());
		String actualProductId = runId.getText();
		Assert.assertEquals(actualProductId.toLowerCase(), this.recipe.getRunId().toLowerCase());
	}
	
	public void timeValidation() {
		Assert.assertTrue(timeValidate.isDisplayed());

	}
	public boolean verifyRestart() {
		return(manualStopButton.waitUntil(Condition.visible,50001).isDisplayed());
	}
	public void incrementTimer(){
		int fristTime = Integer.parseInt(secondValidate.getText());
		int minFirstTime = Integer.parseInt(SelenideHelper.removeLastCharOptional(minuteValidate.getText()));
		Selenide.sleep(2000);
		int secondTime = Integer.parseInt(secondValidate.getText());
		int minSecondTime = Integer.parseInt(SelenideHelper.removeLastCharOptional(minuteValidate.getText()));
		int differ = ( minSecondTime*60 + secondTime) - (minFirstTime*60+fristTime);
		Assert.assertTrue(differ>=2);

	}
	public void stopButton() {
		manualStopButton.click();
		closeButtonOfStop.click();
	}
	public void verifyPostRunDate() throws ParseException {
		String startDate1 = startDate.getText();
		Assert.assertTrue(startDate1.matches(("([0-9]{2})/([aA-zZ]{3})/([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2})"))); 
		postRunCommentsText.sendKeys("completed");   
	}
	public void autoClosePostRun() {
		postRunWindow.waitUntil(Condition.disappear,600001).shouldNot(visible);
	}
	public void validateStartButtonNotSelect(String status) {
		SelenideHelper.commonWaiter(manualStartButton, visible);
		if(status.equalsIgnoreCase("disabled") ) {
			manualStartButton.shouldNotBe(selected);
		}
		else if(status.equalsIgnoreCase("enabled")) {
			manualStartButton.shouldBe(visible);
		}
	}
	
	public void runButton(String status) {
		runIcon.waitUntil(visible, 10000);
		if(status.equalsIgnoreCase("disabled") ) {
			runIcon.shouldNotBe(selected);
		}
		else if(status.equalsIgnoreCase("enabled")) {
			runIcon.shouldBe(visible);
		}
	}
	
}