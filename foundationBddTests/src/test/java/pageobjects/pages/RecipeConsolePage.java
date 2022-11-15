package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import dataobjects.Recipe;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import java.util.Timer;

public class RecipeConsolePage {
    private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
    private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
    private final String XPATH_RECIPE_LOADED_BEFORE = "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";

    private final SelenideElement loadRecipeText = $(By.xpath("//p[text()='Load Recipe']"));
    private final SelenideElement clearRecipeText = $(By.xpath("//p[text()='Clear Panel']"));
    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));

    private final SelenideElement expandIcon = $(By.xpath("//img[@class='jss9']"));
    private final SelenideElement runIcon = $(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement abortIcon = $(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement rerunIcon = $(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement pauseIcon = $(By.xpath("//img[contains(@src,'Group')]"));
    private final SelenideElement resumeIcon = $(By.xpath("//img[contains(@src,'RESUME')]"));
    private final SelenideElement jumpStepIcon = $(By.xpath("//img[contains(@src,'JUMP_STEP')]"));
    private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));
    private final SelenideElement errorMessage = $(By.xpath("//h6[contains(@class,'MuiTypography-subtitle1') and contains(text(),'invalid step number enter')]"));

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
    private final SelenideElement machineName=$(By.xpath("//span[@id='machine_Id']/label"));
    private final String  stepNo = "//p[text()='%S']";
  
    private final SelenideElement manualOperationButton =$(By.xpath("//span[contains(text(),'MANUAL OPERATION')]"));
    private final SelenideElement manualOperationSelected = $(By.xpath("//button[contains(@class, 'MuiButton-outlinedPrimary')]//span[contains(text(),'MANUAL OPERATION')]"));
    private final SelenideElement recipeButton = $(By.xpath("//span[contains(text(),'RECIPE RUN')]"));
    private final SelenideElement recipeButtonSelected = $(By.xpath("//button[@class ='MuiButtonBase-root MuiButton-root MuiButton-outlined jss24 MuiButton-outlinedSecondary']"));
    private final SelenideElement pauseButton = $(By.xpath("//img[@src='/useradminportal/static/media/Group 8.59d83e21.svg']"));
    
    
    
    private final SelenideElement clearRecipeButton = $(By.xpath("//*[contains(@class,'MuiTypography-root') and text()='Clear Panel']"));
    

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
        if(expandIcon.isDisplayed()){
            expandIcon.click();
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

    public void isExecuted() {
        rerunIcon.waitUntil(Condition.visible, 20000l);
    }

    public void clickPauseButton() {
        pauseIcon.waitUntil(visible, 50001).click();
    }

    public void clickResumeButton() {
        resumeIcon.waitUntil(Condition.visible, 5000l).click();
        
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
	
	public void jumpStepErrorMessage() throws InterruptedException {
	    Selenide.sleep(1000);
		SelenideHelper.commonWaiter( errorMessage,visible).wait(1000);
		System.out.println(errorMessage.getText());
		
		
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
	   rerunIcon.waitUntil(visible,50001).click();
   }
   public void processHold() {
	   $(By.xpath(String.format(XPATH_PNID_BUTTON, "PROCESS HOLD"))).click();
   }
   //Need to club with RecipeRun method(visible and disable), currently overloading
   public void recipeRun(String status) {
	   if(status.equalsIgnoreCase("disabled")) {
		   recipeButton.shouldBe(disabled);
	   }
	   else if(status.equalsIgnoreCase("enabled")) {
		   recipeButton.waitUntil(visible, 50001).click();
		   recipeButton.shouldBe(visible);
	   }
   }
   public void selectRecipeOperation(String status) {
	   
   }
}
