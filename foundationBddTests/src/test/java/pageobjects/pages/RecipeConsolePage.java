package pageobjects.pages;

import com.codeborne.selenide.Condition;
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
import com.codeborne.selenide.WebDriverRunner;
import cucumber.util.DriverHooks;
import dataobjects.Recipe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageobjects.utility.SelenideHelper;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
public class RecipeConsolePage {
	private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
	private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
	private final String XPATH_RECIPE_LOADED_BEFORE = "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";
	private final String XPATH_CTRL_ICONS = "//img[contains(@src,'%s')]";
	private final String XPATH_TEXTS = "//p[text()='%s']";

	private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
	private final SelenideElement postRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
	private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));

	private final SelenideElement expandIcon = $(By.xpath("//img[@class='jss9']"));
	private final SelenideElement collapseIcon = $(By.xpath("//img[@class='jss8']"));
	private final SelenideElement manualOperations = $(By.xpath("//span[text()='MANUAL OPERATION']"));
	private final SelenideElement timerValue = $(By.xpath("//div[text()='00:00:00:00']"));
	private final SelenideElement pauseTimerValue = $(By.xpath("//p[contains(text(),'0:00')]"));
	private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));
	private final String errorMessage = "//h6[contains(text(),'invalid step number enter:1-%s')]";
	private final SelenideElement restartButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "RESTART")));
	private final SelenideElement yesButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "Yes")));
	private final SelenideElement holdButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "HOLD")));
	private final SelenideElement loadButton = $(By.xpath("//span[contains(text(),'Load')]"));
	private final SelenideElement okStepButton = $(By.xpath("//span[text()='Ok']"));
	private final SelenideElement clickYesButton = $(By.xpath("//span[text()='Yes']"));
	private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'OK')]"));
	private final SelenideElement abortButton = $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT")));

	private final SelenideElement runIdTextbox = $(By.xpath("//input[@name='runId']"));
	private final SelenideElement productIdTextbox = $(By.xpath("//input[@name='productId']"));
	private final SelenideElement batchIdTextbox = $(By.xpath("(//*[contains(@class,'autocompletediv')]//input)[1]"));
	private final SelenideElement startDate = $(By.xpath("//span[@id='startDate_Id']"));
	private final SelenideElement endDate = $(By.xpath("//span[@id='endDate_Id']"));
	private final SelenideElement machineName = $(By.xpath("//span[@id='machine_Id']/label"));

	private final String jumpStepNo = "//p[text()='%S']";

	private final SelenideElement manualOperationButton = $(By.xpath("//span[contains(text(),'MANUAL OPERATION')]"));
	private final SelenideElement manualOperationSelected = $(By.xpath("//button[contains(@class, 'MuiButton-outlinedPrimary')]//span[contains(text(),'MANUAL OPERATION')]"));
	private final SelenideElement recipeButton = $(By.xpath("//span[contains(text(),'RECIPE RUN')]"));
	private final SelenideElement recipeButtonSelected = $(By.xpath("//button[@class ='MuiButtonBase-root MuiButton-root MuiButton-outlined jss24 MuiButton-outlinedSecondary']"));
	private final SelenideElement pauseButton = $(By.xpath("//img[@src='/useradminportal/static/media/Group 8.59d83e21.svg']"));

	private final SelenideElement clearRecipeButton = $(By.xpath("//*[contains(@class,'MuiTypography-root') and text()='Clear Panel']"));
	private ElementsCollection recipeStepCount = $$(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column']/div/div[@class='MuiGrid-root MuiGrid-container']"));
	private final SelenideElement closeJumpStep = $(By.xpath("//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAFKADAAQAAAABAAAAEwAAAAAA/SztAAABDUlEQVQ4Ea3UUQuCMBDA8UulqKeIkCCQoO//kYIIepGejSKt/oOTabqd0L3opvdrN6/N3t+QbzweT8myVNI0ZTgp6rqW16uWxWIuGZlgp9NZkiSRotjLarU0g1V1l8vlKk3TyPF4kIRMVgbGJA95yRI+Rr5zSKRMVjYF7WPk47gVglKmFR3CdJta0IqGMIwOGENjGPkzbRsGfvST83wrZXlzHy7UDaMguI/qj4Uw3vkpWRO5stGszA/G+gH8eb0PgqyQMv1gzPxYjIJ+uZS52+WmPh0E+xj9udmsTX36Aw5humdcY83fAUOY7lkMbUELZkEdyHmmR1Csz0IojgM5HDm6rNgYitP+U/51Yn8AzV4maDdMLYMAAAAASUVORK5CYII=']"));    
	private final SelenideElement manualStartButton = $(By.xpath("//img[contains(@src,'START_btn.3c28170b.svg')]"));
	private final SelenideElement manualOperationName = $(By.xpath("//input[@name ='recipeName']"));
	private final SelenideElement manualStopButton = $(By.xpath("//img[@src='/useradminportal/static/media/End_btn Copy-End_btn.0328c518.svg']"));
	private final SelenideElement matchId = $(By.xpath("(//label[@id='trimString'])[1]"));
	private final SelenideElement batchId = $(By.xpath("(//label[@id='trimString'])[2]"));
	private final SelenideElement runId = $(By.xpath("(//label[@id='trimString'])[3]"));
	private final SelenideElement timeValidate = $(By.xpath("//div[@id='timerCycle']")); 
	private final SelenideElement secondValidate = $(By.xpath("//div[@id='timerCycle']//span[4]"));
	private final SelenideElement minuteValidate = $(By.xpath("//div[@id='timerCycle']//span[3]"));
	private final SelenideElement closeButtonOfStop = $(By.xpath("//span[contains(text(),'Yes')]"));
	private final SelenideElement postRunWindow = $(By.xpath("//p[contains(text(),'Post-Run Record')]"));

	private final SelenideElement noButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "No")));
	private final SelenideElement closeButton = $(By.xpath("//h6[text()='Load Recipe']/parent::h6/parent::div/following-sibling::div/img"));
	private final SelenideElement status=$(By.xpath("//th[text()='Status']"));
	private final ElementsCollection loadRecipeStatus = $$(By.xpath("//tbody/tr[contains(@class,'MuiTableRow-root')]/td[2]/label"));
	private final String recipeName = "//label[text()='%s']";
	private final SelenideElement stepNo = $(By.xpath("//p[(@class='MuiTypography-root jss1517 MuiTypography-body1')]"));
	private final SelenideElement step = $(By.xpath("//p[(@class='MuiTypography-root jss1516 MuiTypography-body1')]"));
	private final SelenideElement previousStep1 =$(By.xpath("//label[text()='LoopControl→FI102→Configure→Setpoint: 0.0 L/min']"));
	private final SelenideElement currentStep = $(By.xpath("//label[text()='LoopControl→FI101→Configure→Auto']"));
	private final SelenideElement nextStep =$(By.xpath("//label[text()='LoopControl→FI101→Configure→Auto']"));
	private final SelenideElement runStatus = $(By.xpath("//div[@class='displayLabel']/parent::div/div[3]/div[2]/span/label"));
	private final ElementsCollection redmark = $$(By.xpath("//*[contains(@class,'label1')]/span[i]"));
	private final ElementsCollection textBox_RedClrMsg = $$(By.xpath("//span[text()='Mandatory field should not be empty.']"));
	//private final String textBox_RedClrMsg = "//span[text()='Mandatory field should not be empty.']";
	private final SelenideElement existingRunIdMsg =$(By.xpath("//span[text()='Run ID is already in use.']"));
	private final String manualOparation ="//span[text()='%s']";
	private final SelenideElement start_Btn = $(By.xpath("//img[contains(@src,'START')]"));        
	private final SelenideElement preRun_WindowPopop = $(By.xpath("//p[text()='Pre-Run Record']"));
	private final SelenideElement runIdExistMsg = $(By.xpath("//span[text()='Run ID is already in use.']"));
	private final SelenideElement manualOpareationTextbox = $(By.xpath("//input[@name='recipeName']"));
	private final SelenideElement recipeStep =$(By.xpath("//label[contains(@title,'LoopControl→FI101→Configure→Setpoint: 1.0 L/min')]"));
	private final SelenideElement stepsNo = $(By.xpath("//p[@class='MuiTypography-root jss101 MuiTypography-body1']"));
	private final SelenideElement mainPage =$(By.xpath("//div[@id='PNID']"));
	private final SelenideElement processHoldDailogBox=$(By.xpath("//h6[text()='Are you sure you want to put the process on hold?']"));
	private final SelenideElement processhold_Box=$(By.xpath("//h6[text()='Process Hold']"));
	private final SelenideElement ProcessHold = $(By.xpath("//span[text()='PROCESS HOLD']"));

	private ElementsCollection approvedActive = $$(By.xpath("//tbody[(@class='MuiTableBody-root jss228')]/tr/td[i]"));
	private final SelenideElement clearRecipeText = $(By.xpath("//p[text()='Clear Panel']"));
	private final SelenideElement loadRecipeText = $(By.xpath("//p[text()='Load Recipe']"));
	private final SelenideElement processRestart = $(By.xpath("//span[text()='PROCESS RESTART']"));
	private final SelenideElement processRestartMsg = $(By.xpath("//h6[contains(text(),'Are you sure you want to restart the process?')]"));
	private final SelenideElement processRestartWindowPopup = $(By.xpath("//h6[text()='Process Restart']"));
	//	private final SelenideElement no_Btn = $(By.xpath("//span[text()='No']"));
	//	private final SelenideElement yes_Btn = $(By.xpath("//span[text()='Yes']"));

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
		SelenideHelper.commonWaiter(yesButton, visible).click();
	}

	public void holdSystem() {
		SelenideHelper.commonWaiter(holdButton, visible).click();
		SelenideHelper.commonWaiter(yesButton, visible).click();
	}

	public void gotoRecipeConsole() {
		if (!collapseIcon.isDisplayed()) {
			SelenideHelper.commonWaiter(expandIcon, visible).click();
		}
		if($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
			$(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
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
		if(abortButton.isDisplayed()) {
			abortButton.click();
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

	public void gotoManualOperations() {
		SelenideHelper.commonWaiter(manualOperations, visible).click();
	}

	public void verifyManualRunOptions() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "START"))).shouldBe(visible);
		$(By.xpath(String.format(XPATH_TEXTS, "BATCH ID"))).shouldBe(visible);
		$(By.xpath(String.format(XPATH_TEXTS, "RUN ID"))).shouldBe(visible);
		$(By.xpath(String.format(XPATH_TEXTS, "DHMS"))).shouldBe(visible);
		timerValue.shouldBe(visible);

	}

	public String startAndWaitRecipe(Recipe recipe, int seconds) {

		String runId;
		String[] dateParts = null;
		String[] dateparts1 = null;

		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
		runId = runIdTextbox.getValue();
		productIdTextbox.setValue(recipe.getProductId());
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(recipe.getBatchId());
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(recipe.getBeforeComments());
		okButton.click();
		abortButton.waitUntil(Condition.visible, 5000l);
		abortButton.waitUntil(Condition.not(Condition.visible), seconds * 2000l);
		SelenideHelper.commonWaiter(startDate, visible);
		recipe.setStartDate(startDate.getText());
		recipe.setEndDate(endDate.getText());
		recipe.setMachineName(machineName.getText());
		recipe.setStatus(executionStatusText.getText());
		preRunCommentsText.sendKeys(recipe.getAfterComments());
		okButton.click();

		return runId;
	}
	public void startRecipe(String productId, String batchId, String beforeComments) {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(beforeComments);
		okButton.click();
	}
	public void isExecuted() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 5000l);
	}
	public void isExecuted(int seconds) {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, seconds * 1000l);
	}

	public void clickPauseButton() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible, 5000l).click();
	}

	public void clickResumeButton() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible, 5000l).click();
	}

	public boolean verifyResumeButton() {
		SelenideHelper.takePicture();
		return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible, 50001).isDisplayed());
	}

	public boolean verifyPauseButton() {
		return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible, 50001).isDisplayed());
	}

	public boolean verifyReRunButton() {
		return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 50001).isDisplayed());
	}

	public void clickOnJumpToStep(String stepNumber) {
		Selenide.sleep(2000);
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).waitUntil(Condition.visible, 4000l, 50l).click();
		inputStepNumber.waitUntil(Condition.visible, 4000l, 50l);
		Selenide.sleep(1000);
		inputStepNumber.sendKeys(stepNumber);
		okStepButton.waitUntil(Condition.visible, 4000l, 500).click();
		abortButton.waitUntil(visible, 4000l, 500);
		Selenide.sleep(1000);
		SelenideHelper.takePicture();
	}

	public void clickOnAbortButton(String afterComments) {
		abortButton.waitUntil(Condition.visible, 10000l).click();
		clickYesButton.waitUntil(Condition.visible, 5000l).click();
		SelenideHelper.commonWaiter(preRunCommentsText, visible).sendKeys(afterComments);
	}

	public void seeSystemOnHold() {
		SelenideHelper.commonWaiter(restartButton, visible);
	}

	public String getExecutionStatusText() {
		commonWaiter(executionStatusText,visible);
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
	public void clearRecipe() {
		SelenideHelper.commonWaiter(clearRecipeButton, visible).click();
	}

	public void jumpStepErrorMessage(){
		var stepCount = recipeStepCount.size();
		$(By.xpath(String.format(errorMessage,stepCount))).isDisplayed();
		closeJumpStep.click();
	}
	public void verifyStep(String stepNumber) {
		clickOnJumpToStep(stepNumber);
		var reviewStatus = $(By.xpath(String.format(jumpStepNo,stepNumber)));
		SelenideHelper.commonWaiter(reviewStatus, visible);

	}
	public void manualOperation(String status) {

		if(status.equalsIgnoreCase("enabled")) {
			manualOperationButton.waitUntil(visible, 50001).click();
			manualOperationSelected.shouldBe(visible);
		}
		else if(status.equalsIgnoreCase("disabled")) {
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
	public void verifyJumpStep() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).shouldNotBe(selected);
	}
	public void clearPanelAndRunDisabled() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).shouldNotBe(selected);
		$(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).shouldNotBe(selected);
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
	public boolean verifyApprovedRecipe() {	
		boolean isResult=false;
		SelenideHelper.commonWaiter(status, visible);
		ElementsCollection options = loadRecipeStatus;	
		if(options.size()==4) {

		}
		for (SelenideElement element : options) {
			if(element.getText().equalsIgnoreCase("Approved-Active")) {
				isResult=true;}
			else {
				isResult=false;
				break;
			}
		}
		return isResult;
	}

	public void recipeNameInConsol(String recipename) {
		$(By.xpath(String.format(recipeName, recipeName))).shouldBe(visible);
		recipeDetails();
	}

	public void recipeDetails() {

		if(step.isDisplayed()) {
			stepNo.shouldBe(visible);
		}else {
			stepNo.shouldNotBe(visible);
		}

	}

	public void recipeStepdetails() {
		previousStep1.shouldNotBe(visible);
		currentStep.shouldBe(visible);
		nextStep.shouldNotBe(visible);		
	}
	public void iClickMaualOperation(String name) {

		$(By.xpath(String.format(manualOparation, name))).click();
	}

	public void startButton() {

		start_Btn.shouldBe(visible);
	}

	public void iValidateStart() {
		SelenideHelper.commonWaiter(start_Btn, visible);
	}

	public void preRunWindow_Popup() {
		preRun_WindowPopop.shouldBe(visible);
	}

	public void okButton() {
		SelenideHelper.commonWaiter(okButton, visible).click();
	}
	public boolean validateHilightedMsg(String message) {
		boolean isResult=false;
		//SelenideHelper.commonWaiter(status, visible);
		ElementsCollection options = textBox_RedClrMsg;	
		for (SelenideElement element : options) {
			if(element.getText().equalsIgnoreCase(message)) {
				isResult=true;}
			else {
				isResult=false;
				break;
			}
		}
		return isResult;
	}
	public void runId(String value) {
		SelenideHelper.commonWaiter(runIdTextbox, visible).click();
		System.out.println("Click on RunID");
		Selenide.sleep(2000);
		runIdTextbox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		System.out.println("Clear RinID");
		runIdTextbox.sendKeys(value);
		Selenide.sleep(2000);
		runIdTextbox.sendKeys(Keys.ENTER);
		clickOnClose();
	}

	public void runIdManual(String value) {
		SelenideHelper.commonWaiter(runIdTextbox, visible).click();
		Selenide.sleep(2000);
		runIdTextbox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		runIdTextbox.sendKeys(value);
		Selenide.sleep(2000);
		runIdTextbox.sendKeys(Keys.ENTER);
		
	}
	
	public void uniqBatchId(String batchId) {
		SelenideHelper.commonWaiter(batchIdTextbox, visible).click();
		Selenide.sleep(2000);
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		//Selenide.sleep(2000);
		okButton.click();
	}

	public void existingBachId(String productId,String beforeComments) {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).click();
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(Keys.ENTER);
		batchIdTextbox.sendKeys(Keys.ARROW_DOWN);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(beforeComments);
		okButton.click();
	}

	public void validateMsg(String message) {
		if(runIdExistMsg.getText().equalsIgnoreCase(message)){
			runIdExistMsg.shouldBe(visible);
		}else {
			runIdExistMsg.shouldNotBe(visible);
		}
		Selenide.sleep(2000);
		SelenideHelper.commonWaiter(runIdTextbox, visible).click();
		Selenide.sleep(2000);
		SelenideHelper.commonWaiter(runIdTextbox, visible).clear();
	}
	public void manualValidation(String ManualOperationName,String runId,String batchId,String productId,String beforeComments) {
		manualOpareationTextbox.click();
		manualOpareationTextbox.setValue(ManualOperationName);
		runIdTextbox.setValue(runId);
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(beforeComments);
	}

	public void iValidateSpecialChar_manaul(String ManualOperationName,String runId,String batchId,String productId,String value) {
		manualOpareationTextbox.click();
		manualOpareationTextbox.setValue(ManualOperationName);
		runIdTextbox.setValue(runId);
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(value);
		SelenideHelper.commonWaiter(okButton, visible).click();

	}

	public void iValidateSpecialChar_run(String runId,String batchId,String productId,String value) {
		runIdTextbox.setValue(runId);
		productIdTextbox.setValue(productId);
		batchIdTextbox.click();
		batchIdTextbox.sendKeys(batchId);
		batchIdTextbox.sendKeys(Keys.ENTER);
		preRunCommentsText.sendKeys(value);
		SelenideHelper.commonWaiter(okButton, visible).click();
	}
	public void iValidationPreRun() {
		if(preRun_WindowPopop.isDisplayed()) {
			preRun_WindowPopop.shouldBe(visible);	
		}else {
			preRun_WindowPopop.shouldNotBe(visible);
		}
	}

	public void clickOnLoadRecipe() {		
		if (restartButton.isDisplayed()) {
			restartSystem();
			SelenideHelper.commonWaiter(holdButton, visible);
		}
		if (clearRecipeText.isDisplayed()) {
			clearRecipeText.click();
		}
		loadRecipeText.click();
		loadButton.waitUntil(Condition.visible, 20000l);

	}

	public boolean verifyRecipeDetails(String batch_Id) {
		boolean isResult=false;
		if(batchId.getText().equalsIgnoreCase(batch_Id) && recipeStep.isDisplayed()) {
			isResult=true;
			System.out.println(batchId);
			System.out.println(batch_Id);
		}
		return isResult;
		
		
		
	}

	public void clickOnClose() {
		closeButton.waitUntil(Condition.visible, 5000l).click();
	}

	public boolean verifyLivestepDetails(String steps ) {
		boolean isResult=false;
		if(stepNo.getText().equalsIgnoreCase(steps) && recipeStep.isDisplayed()) {
			isResult=true;
		}
		return isResult;
	}

	public void mainPage() {
		SelenideHelper.commonWaiter(mainPage, visible).click();

	}
	public void start_button() {
		if (restartButton.isDisplayed()) {
			restartSystem();
			SelenideHelper.commonWaiter(holdButton, visible);
		}
		if (clearRecipeText.isDisplayed()) {
			clearRecipeText.click();
		}else
			SelenideHelper.commonWaiter(start_Btn, visible).click();
	}
	public void run_Btn() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
	}
	public boolean iverifyDailogBox() {
		boolean isResult=false;
		if(processhold_Box.isDisplayed()) {

			SelenideHelper.commonWaiter(processHoldDailogBox, visible);
			isResult=true;
		}else {
			processHoldDailogBox.shouldNotBe(visible);
		}
		return isResult;
	}

	public void validateNoBtn() {
		SelenideHelper.commonWaiter(noButton, visible).click();
	}
	public void validateYesBtn() {
		SelenideHelper.commonWaiter(yesButton, visible).click();
	}

	public boolean processRestart() {
		boolean isResult=false;
		if(!processhold_Box.isDisplayed()) {
			processRestart.shouldBe(visible);
			isResult=true;
		}else {
			processRestart.shouldNotBe(visible);
		}
		return isResult;
	}

	public boolean iVerifyRecipeConsoleElement() {
		boolean isResult=false;
		if(processhold_Box.isDisplayed()) {
			recipeButton.shouldNot(visible);
			manualOperations.shouldNotHave(visible);
			isResult=true;
		}else {
			recipeButton.shouldBe(visible);
			manualOperations.shouldBe(visible);
		}
		return isResult;
	}

	public void iSelectProcessRestart() {
		SelenideHelper.commonWaiter(processRestart, visible).click();
	}

	public boolean iVerifyProcessRestartPopup() {
		boolean isResult=false;
		if(processRestartWindowPopup.isDisplayed()) {
			processRestartMsg.shouldBe(visible);
			SelenideHelper.commonWaiter(noButton, visible);
			SelenideHelper.commonWaiter(yesButton, visible);
			isResult=true;
		}
		return isResult;
	}

	public boolean iVerifyProcessRestartToProcessHold() {
		boolean isResult=false;
		if(ProcessHold.isDisplayed()) {
			recipeButton.shouldBe(visible);
			manualOperations.shouldBe(visible);
			isResult=true;
		}else {
			recipeButton.shouldNotBe(visible);
			manualOperations.shouldNotBe(visible);
		}
		return isResult;
	}
	
	public void iRefreshPortal() {
		Selenide.refresh();
	}
}
