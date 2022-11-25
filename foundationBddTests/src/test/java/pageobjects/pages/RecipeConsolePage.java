package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;

import dataobjects.Recipe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import java.util.*;
import java.util.Date;
import static pageobjects.utility.SelenideHelper.commonWaiter;


import java.util.Timer;


public class RecipeConsolePage {
    private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
    private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
    private final String XPATH_RECIPE_LOADED_BEFORE = "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";
    private final String XPATH_CTRL_ICONS = "//img[contains(@src,'%s')]";
    private final String XPATH_TEXTS = "//p[text()='%s']";
    private final String XPATH_RESTART_OR_HOLD_BUTTON = "//span[@class='MuiButton-label']";
    
    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));

    private final SelenideElement expandIcon = $(By.xpath("//img[@class='jss9']"));
    private final SelenideElement collapseIcon = $(By.xpath("//img[@class='jss8']"));
    private final SelenideElement manualOperations = $(By.xpath("//span[text()='MANUAL OPERATION']"));
    private final SelenideElement timerValue = $(By.xpath("//div[text()='00:00:00:00']"));
    private final SelenideElement pauseTimerValue = $(By.xpath("//p[contains(text(),'0:00')]"));
    private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));

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

    private void holdSystem() {
        SelenideHelper.commonWaiter(holdButton, visible).click();
        SelenideHelper.commonWaiter(yesButton, visible).click();
    }

    public void gotoRecipeConsole() {
    	SelenideHelper.commonWaiter(expandIcon, visible).click();
    }
    
    public void collapseRecipeConsole() {
    	SelenideHelper.commonWaiter(collapseIcon, visible).click();
    }

    public void loadRecipe(String recipeName) {
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
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
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.visible, 5000l);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.not(Condition.visible), seconds * 2000l);
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

        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
        runId = runIdTextbox.getValue();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);        
        okButton.click();
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.visible, 5000l);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.not(Condition.visible), seconds * 1000l);
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

    public void clickPauseButton() {
    	$(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible, 5000l).click();
    }

    public void clickResumeButton() {
    	$(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible, 5000l).click();
    }
    
    public boolean verifyResumeButton() {
        return($(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible,50001).isDisplayed());
    }
    
    public boolean verifyPauseButton() {
        return($(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible,50001).isDisplayed());
    }
    
    public boolean verifyReRunButton() {
        return($(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible,50001).isDisplayed());
    }

    public void clickOnJumpToStep(String stepNumber) {
        Selenide.sleep(2000);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).waitUntil(Condition.visible, 4000l, 50l).click();
        inputStepNumber.waitUntil(Condition.visible, 4000l, 50l);
        Selenide.sleep(1000);
        inputStepNumber.sendKeys(stepNumber);
        okStepButton.waitUntil(Condition.visible, 4000l).click();
    }

    public void clickOnAbortButton(String afterComments) {
    	$(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.visible, 6000l).click();
        clickYesButton.waitUntil(Condition.visible, 5000l).click();
        SelenideHelper.commonWaiter(preRunCommentsText, visible).sendKeys(afterComments);
    }

    public void seeSystemOnHold() {
        SelenideHelper.commonWaiter(restartButton, visible);
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
	
	public void clearPanelAndRunDisabled() {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).shouldNotBe(selected);
		$(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).shouldNotBe(selected);
    }
	
	public void recipeisPaused() throws ParseException {
		$(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).shouldNotBe(selected);
    	SimpleDateFormat formatter = new SimpleDateFormat("DD:HH:MM:SS");
    	String timeStart = pauseTimerValue.getText();
    	Selenide.sleep(2000);
    	String timeEnd   = pauseTimerValue.getText();
    	Date startTime=  formatter.parse(timeStart);
    	Date endTime = formatter.parse(timeEnd);
    	long timeDifference = (endTime).getTime() - (startTime).getTime(); 
    	if (timeDifference>0) {
    		Assert.assertTrue(true);
    	}
    	else{
    		Assert.assertTrue(false);
    	}
    }
	
	public void hold() {
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
        holdSystem();
    }

	public void clearRecipe() {
		SelenideHelper.commonWaiter(clearRecipeButton, visible).click();
	}

}
