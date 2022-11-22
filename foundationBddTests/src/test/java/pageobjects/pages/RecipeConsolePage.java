package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import com.codeborne.selenide.SelenideElement;
import dataobjects.Recipe;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    private final SelenideElement loadRecipeText = $(By.xpath("//p[text()='Load Recipe']"));
    private final SelenideElement clearRecipeText = $(By.xpath("//p[text()='Clear Panel']"));
    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement postRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));

    private final SelenideElement expandIcon = $(By.xpath("//img[@class='jss9']"));
    private final SelenideElement runIcon = $(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement abortIcon = $(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement rerunIcon = $(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement pauseIcon = $(By.xpath("//img[contains(@src,'Group')]"));
    private final SelenideElement resumeIcon = $(By.xpath("//img[contains(@src,'RESUME')]"));
    private final SelenideElement jumpStepIcon = $(By.xpath("//img[contains(@src,'JUMP_STEP')]"));
    private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));
    private final String errorMessage = "//h6[contains(text(),'invalid step number enter:1-%s')]";
    private final SelenideElement errorRecipeStepMessage = $(By.xpath("(//*[contains(@class,'MuiTypography-subtitle1')])[6]"));
    private final SelenideElement restartButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "RESTART")));
    private final SelenideElement yesButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "Yes")));
    private final SelenideElement holdButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "HOLD")));
    private final SelenideElement loadButton = $(By.xpath("//span[contains(text(),'Load')]"));
    private final SelenideElement okStepButton = $(By.xpath("//span[text()='Ok']"));
    private final SelenideElement clickYesButton = $(By.xpath("//span[text()='Yes']"));
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'OK')]"));

    private final SelenideElement runIdTextbox = $(By.xpath("//input[@name='runId']"));
    private final SelenideElement productIdTextbox = $(By.xpath("//input[@name='productId']"));
    private final SelenideElement batchIdTextbox = $(By.xpath("(//*[contains(@class,'autocompletediv')]//input)[1]"));
    private final SelenideElement startDate = $(By.xpath("//span[@id='startDate_Id']"));
    private final SelenideElement endDate = $(By.xpath("//span[@id='endDate_Id']"));
    private final SelenideElement machineName = $(By.xpath("//span[@id='machine_Id']/label"));
    private final String stepNo = "//p[text()='%S']";

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
    private final SelenideElement closeButtonOfStop = $(By.xpath("//span[contains(text(),'Yes')]"));
    private final SelenideElement postRunWindow = $(By.xpath("//p[contains(text(),'Post-Run Record')]"));

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
        expandIcon.click();
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
    }

    public void loadRecipe(String recipeName) {
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
        if (clearRecipeText.isDisplayed()) {
            clearRecipeText.click();
        }
        loadRecipeText.click();
        loadButton.waitUntil(Condition.visible, 20000l);
        $(By.xpath(String.format(XPATH_LOAD_RECIPE, recipeName))).click();
        loadButton.click();
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
        okButton.click();
        abortIcon.waitUntil(Condition.visible, 5000l);
        abortIcon.waitUntil(Condition.not(Condition.visible), seconds * 2000l);
        SelenideHelper.commonWaiter(startDate, visible);
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
        SelenideHelper.commonWaiter(startDate, visible);
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
    }

    public void isExecuted(int seconds) {
        rerunIcon.waitUntil(Condition.visible, seconds * 1000l);
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
        okStepButton.waitUntil(Condition.visible, 4000l).click();
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
	   var reviewStatus = $(By.xpath(String.format(stepNo,stepNumber)));
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
	   rerunIcon.click();
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
	   Selenide.sleep(2000);
	   int secondTime = Integer.parseInt(secondValidate.getText());
	   int differ = secondTime - fristTime;
		Assert.assertTrue(differ<=2);
		Assert.assertTrue(differ>1);
   }
   public void stopButton() {
	   manualStopButton.click();
	   closeButtonOfStop.click();
   }
   public void verifyPostRunDate() {
	   DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	   String startDate1 = startDate.getText();
	   String date1= dateFormat.format(startDate1);
       Assert.assertEquals(startDate1, date1);
	   postRunCommentsText.sendKeys("completed");   
   }
   public void autoClosePostRun() {
	   postRunWindow.waitUntil(Condition.disappear,600001).shouldNot(visible);
   }
   public void validateStartButtonNotSelect(String status1,String status2) {
	   
	   if(status1.equalsIgnoreCase("displayed")&& status2.equalsIgnoreCase("disabled") ) {
	   manualStartButton.exists();
	   manualStartButton.shouldNot(selected);
	   }
	   else if(status1.equalsIgnoreCase("displayed")&& status2.equalsIgnoreCase("enabled")) {
		   manualStartButton.exists();
		   manualStartButton.shouldBe(selected);
	   }
   }
}
