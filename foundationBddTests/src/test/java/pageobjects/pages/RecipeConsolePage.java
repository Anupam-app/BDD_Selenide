package pageobjects.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pageobjects.utility.SelenideHelper.commonWaiter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import dataobjects.Recipe;
import pageobjects.utility.SelenideHelper;

public class RecipeConsolePage {
    Actions stepAction = new Actions(WebDriverRunner.getWebDriver());
    private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
    private final String XPATH_RESTART_OR_HOLD_BUTTON = "//span[@class='MuiButton-label']";
    private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
    private final String XPATH_RECIPE_LOADED_BEFORE =
            "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";
    private final String XPATH_CTRL_ICONS = "//img[contains(@src,'%s')]";
    private final String XPATH_TEXTS = "//p[text()='%s']";

    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement postRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));
    private final SelenideElement reEstablishStateButton =
            $(By.xpath("(//*[contains(@class, 'PrivateSwitchBase-input')])[1]"));
    private final SelenideElement confirmButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "Confirm")));
    private final ElementsCollection recipeListTable = $$(By.xpath("//tbody/tr"));
    private final String recipeListTableValues = "//tbody/tr[%d]/td[%d]/label";
    private final SelenideElement runIcon = $(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement rerunIcon = $(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement abortIcon = $(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement collapseIcon = $(By.xpath("//img[@class='jss8']"));
    private final SelenideElement runCommentErrorMsg =
            $(By.xpath("//span[(text()='Special characters are not allowed for Comments')]"));
    private final SelenideElement expandIcon = $(By.xpath("//img[contains(@class,'jss') and contains(@src,'Tab')]"));
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
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));
    private final SelenideElement abortButton = $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT")));
    private final SelenideElement recipeRunId = $(By.xpath("(//label[@id='trimString'])[4]"));
    private final SelenideElement runIdTextbox = $(By.xpath("//input[@name='runId']"));
    private final SelenideElement productIdTextbox = $(By.xpath("//input[@name='productId']"));
    private final SelenideElement batchIdTextbox = $(By.xpath("(//*[contains(@class,'autocompletediv')]//input)[1]"));
    private final SelenideElement startDate = $(By.xpath("//span[@id='startDate_Id']"));
    private final SelenideElement endDate = $(By.xpath("//span[@id='endDate_Id']"));
    private final SelenideElement machineName = $(By.xpath("//span[@id='machine_Id']/label"));

    private final String jumpStepNo = "//p[text()='%S']";

    private final SelenideElement manualOperationButton = $(By.xpath("//span[contains(text(),'MANUAL OPERATION')]"));
    private final SelenideElement manualOperationSelected = $(By.xpath(
            "//button[contains(@class, 'MuiButton-outlinedPrimary')]//span[contains(text(),'MANUAL OPERATION')]"));
    private final SelenideElement recipeButton = $(By.xpath("//span[contains(text(),'RECIPE RUN')]"));
    private final SelenideElement pauseButton =
            $(By.xpath("//img[@src='/useradminportal/static/media/Group 8.569b9a4c.svg']"));

    private final SelenideElement clearRecipeButton =
            $(By.xpath("//*[contains(@class,'MuiTypography-root') and text()='Clear Panel']"));
    private ElementsCollection recipeStepCount = $$(By.xpath(
            "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column']/div/div[@class='MuiGrid-root MuiGrid-container']"));
    private final SelenideElement closeJumpStep = $(By.xpath(
            "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAFKADAAQAAAABAAAAEwAAAAAA/SztAAABDUlEQVQ4Ea3UUQuCMBDA8UulqKeIkCCQoO//kYIIepGejSKt/oOTabqd0L3opvdrN6/N3t+QbzweT8myVNI0ZTgp6rqW16uWxWIuGZlgp9NZkiSRotjLarU0g1V1l8vlKk3TyPF4kIRMVgbGJA95yRI+Rr5zSKRMVjYF7WPk47gVglKmFR3CdJta0IqGMIwOGENjGPkzbRsGfvST83wrZXlzHy7UDaMguI/qj4Uw3vkpWRO5stGszA/G+gH8eb0PgqyQMv1gzPxYjIJ+uZS52+WmPh0E+xj9udmsTX36Aw5humdcY83fAUOY7lkMbUELZkEdyHmmR1Csz0IojgM5HDm6rNgYitP+U/51Yn8AzV4maDdMLYMAAAAASUVORK5CYII=']"));
    private final SelenideElement manualStartButton = $(By.xpath("//img[contains(@src,'START_btn.3c28170b.svg')]"));
    private final SelenideElement manualOperationName = $(By.xpath("//input[@name ='recipeName']"));
    private final SelenideElement manualStopButton =
            $(By.xpath("//img[@src='/useradminportal/static/media/End_btn Copy-End_btn.0328c518.svg']"));
    private final SelenideElement matchId = $(By.xpath("(//label[@id='trimString'])[1]"));
    private final SelenideElement batchId = $(By.xpath("(//label[@id='trimString'])[2]"));
    private final SelenideElement runId = $(By.xpath("(//label[@id='trimString'])[3]"));
    private final SelenideElement timeValidate = $(By.xpath("//div[@id='timerCycle']"));
    private final SelenideElement closeButtonOfStop = $(By.xpath("//span[contains(text(),'Yes')]"));
    private final SelenideElement postRunWindow = $(By.xpath("//p[contains(text(),'Post-Run Record')]"));
    private final SelenideElement recipeRunBatchId = $(By.xpath("(//label[@id='trimString'])[3]"));
    private final SelenideElement noButton = $(By.xpath(String.format(XPATH_PNID_BUTTON, "No")));
    private final SelenideElement closeButton =
            $(By.xpath("//h6[text()='Load Recipe']/parent::h6/parent::div/following-sibling::div/img"));
    private final SelenideElement status = $(By.xpath("//th[text()='Status']"));
    private final ElementsCollection loadRecipeStatus =
            $$(By.xpath("//tbody/tr[contains(@class,'MuiTableRow-root')]/td[2]/label"));
    private final ElementsCollection textBox_RedClrMsg =
            $$(By.xpath("//span[text()='Mandatory field should not be empty.']"));
    private final SelenideElement start_Btn = $(By.xpath("//img[contains(@src,'START')]"));
    private final SelenideElement preRunWindowPopop = $(By.xpath("//p[text()='Pre-Run Record']"));
    private final SelenideElement runIdExistMsg = $(By.xpath("//span[text()='Run ID is already in use.']"));
    private final SelenideElement manualOpareationTextbox = $(By.xpath("//input[@name='recipeName']"));
    private final SelenideElement recipeStep =
            $(By.xpath("//label[contains(@title,'LoopControl→FI101→Configure→Setpoint: 1.0 L/min')]"));
    private final SelenideElement mainPage = $(By.xpath("//div[@id='PNID']"));
    private final SelenideElement processHoldDailogBox =
            $(By.xpath("//h6[text()='Are you sure you want to put the process on hold?']"));
    private final SelenideElement processhold_Box = $(By.xpath("//h6[text()='Process Hold']"));
    private final SelenideElement clearRecipeText = $(By.xpath("//p[text()='Clear Panel']"));
    private final SelenideElement loadRecipeText = $(By.xpath("//p[text()='Load Recipe']"));
    private final SelenideElement processRestart = $(By.xpath("//span[text()='PROCESS RESTART']"));
    private final SelenideElement processRestartMsg =
            $(By.xpath("//h6[contains(text(),'Are you sure you want to restart the process?')]"));
    private final SelenideElement processRestartWindowPopup = $(By.xpath("//h6[text()='Process Restart']"));
    private final SelenideElement endManualOperation = $(By.xpath("//h6[text()='End Manual Operation']"));
    private final SelenideElement quitEndMAnualOperation = $(By.xpath("//img[contains(@src,'data:image/png;')]"));
    private final SelenideElement recipeNameTrimmed =
            $(By.xpath("//label[@class='text-ellipsis'][text()='testRecipeWithChar30NameLengt']"));
    private final SelenideElement postRunId = $(By.xpath("(//div[@class='postrunFormDetails']/span/label)[2]"));
    private final String postRunDetails = "(//div[@class='postrunFormDetails']/span/label)[%s]";
    private final SelenideElement manualOperation_lengthychar =
            $(By.xpath("//span[text()='Manual Operation Name should not exceed 30 characters.']"));
    private final SelenideElement manualWindowPopup = $(By.xpath("//div[@class='roleHeadline']"));
    private final SelenideElement manualWindowPopup_Btn = $(By.xpath("//button[@class='roleBtnSave']"));
    private final String stepIdDetails = "(//label[@id='trimString'])[%s]";
    private final String timeDetails = "//div[@id='timerCycle']//span[%s]";
    private SelenideElement specialCharactarErrorMsg =
            $(By.xpath("//span[text()='Special characters are not allowed for Comments']"));
    private final SelenideElement ManualOperationRecipe = matchId;
    private final SelenideElement RunID = recipeRunBatchId;
    private final SelenideElement BatchID = $(By.xpath("(//label[@id='trimString'])[2]"));
    private final SelenideElement ConditionalStatement = $(By.xpath("//label[text()='Mobius® Cell Retention System']"));

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
        SelenideHelper.commonWaiter(restartButton, visible)
                .click();
        SelenideHelper.commonWaiter(reEstablishStateButton, enabled)
                .click();
        SelenideHelper.commonWaiter(confirmButton, visible)
                .click();
        holdButton.waitUntil(visible, 10000);
    }

    public void holdSystem() {
        SelenideHelper.commonWaiter(holdButton, visible)
                .click();
        SelenideHelper.commonWaiter(yesButton, visible)
                .click();
        SelenideHelper.commonWaiter(restartButton, visible);
    }

    public void gotoRecipeConsole() {
        if (expandIcon.isDisplayed()) {
            SelenideHelper.commonWaiter(expandIcon, visible)
                    .click();
        }
    }

    public void collapseRecipeConsole() {
        SelenideHelper.commonWaiter(collapseIcon, visible)
                .click();
    }

    public void loadRecipe(String recipeName) {
        if (abortButton.isDisplayed()) {
            abortButton.click();
            clickYesButton.waitUntil(Condition.visible, 1000)
                    .click();
            okButton.waitUntil(Condition.visible, 5001)
                    .click();
        }
        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            okButton.click();
        }
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
        if (manualStartButton.isDisplayed()) {
            recipeButton.click();
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

    public String startAndPauseRecipe(Recipe recipe, int seconds) {
        String runId = startRecipe(recipe);
        pauseButton.waitUntil(visible, seconds * 1000L, 1000L)
                .click();
        return runId;
    }

    public void verifyManualRunOptions() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "START"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "Batch ID"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "Run ID"))).shouldBe(visible);
        $(By.xpath(String.format(XPATH_TEXTS, "DHMS"))).shouldBe(visible);
        timerValue.shouldBe(visible);
    }

    public String startAndWaitRecipe(Recipe recipe, int seconds) {
        String runId = startRecipe(recipe);
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

    public String startRecipe(Recipe recipe) {
        // take clear panel css class when disabled
        clearRecipeButton.waitUntil(visible, 15000l, 1000l);
        var classClearRecipeButton = clearRecipeButton.getAttribute("class");
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000l);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
        preRunWindowPopop.waitUntil(visible, 2000L);
        String runId = runIdTextbox.getValue();
        productIdTextbox.setValue(recipe.getProductId());
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(recipe.getBatchId());
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(recipe.getBeforeComments());
        okButton.waitUntil(visible, 10000, 2000);
        okButton.click();
        abortButton.waitUntil(Condition.visible, 5000l);

        // wait clean panel to be disabled via css class
        SelenideHelper.fluentWaiter()
                .until((webDriver) -> clearRecipeButton.getAttribute("class")
                        .equals(classClearRecipeButton));

        return runId;

    }

    public void isExecuted() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 5000l);
    }

    public void isExecuted(int seconds) {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, seconds * 1000l);
    }

    public void clickPauseButton() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible, 4000l)
                .click();
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(not(visible), 2000l);
    }

    public void clickResumeButton() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible, 5000l)
                .click();
    }

    public boolean verifyResumeButton() {
        return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "RESUME"))).waitUntil(Condition.visible, 50001)
                .isDisplayed());
    }

    public boolean verifyPauseButton() {
        return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "Group"))).waitUntil(Condition.visible, 50001)
                .isDisplayed());
    }

    public boolean verifyReRunButton() {
        return ($(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 50001)
                .isDisplayed());
    }

    public void clickOnJumpToStep(String stepNumber) {
        Selenide.sleep(2000);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).waitUntil(Condition.visible, 4000l, 50l)
                .click();
        inputStepNumber.waitUntil(Condition.visible, 4000l, 50l);
        Selenide.sleep(1000);
        inputStepNumber.sendKeys(stepNumber);
        okStepButton.waitUntil(Condition.visible, 4000l, 500)
                .click();
        abortButton.waitUntil(visible, 4000l, 500);
        Selenide.sleep(2000);
    }

    public void seeSystemOnHold() {
        holdButton.waitUntil(appear, 10000);
    }

    public void seeSystemOnRestart() {
        restartButton.waitUntil(appear, 10000);
    }

    public String getExecutionStatusText() {
        commonWaiter(executionStatusText, visible);
        return executionStatusText.getText();
    }

    public void clickOnAbortButton(String afterComments) {
        abortButton.waitUntil(Condition.visible, 10000l)
                .click();
        clickYesButton.waitUntil(Condition.visible, 5000l)
                .click();
        SelenideHelper.commonWaiter(preRunCommentsText, visible)
                .sendKeys(afterComments);
    }

    public void clickOnOk() {
        okButton.waitUntil(Condition.visible, 5000l)
                .click();
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
        if (clearRecipeButton.isDisplayed() && clearRecipeButton.isEnabled()) {
            // to avoid exception if not clickable
            JavascriptExecutor ex = (JavascriptExecutor) WebDriverRunner.getWebDriver();
            ex.executeScript("arguments[0].click()", clearRecipeButton);
        }
    }

    public void jumpStepErrorMessage() {
        var stepCount = recipeStepCount.size();
        $(By.xpath(String.format(errorMessage, stepCount))).isDisplayed();
        closeJumpStep.click();
    }

    public void verifyStep(String stepNumber) {
        clickOnJumpToStep(stepNumber);
        var reviewStatus = $(By.xpath(String.format(jumpStepNo, stepNumber)));
        SelenideHelper.commonWaiter(reviewStatus, visible);

    }

    public void manualOperation(String status) {
        if (status.equalsIgnoreCase("enabled")) {
            manualOperationButton.waitUntil(visible, 100001)
                    .click();
            manualOperationSelected.shouldBe(visible);
        } else if (status.equalsIgnoreCase("disabled")) {
            manualOperationButton.shouldNotBe(selected);
        }
    }

    public void pauseButton() {
        pauseButton.waitUntil(visible, 50001);
    }

    public void reRun() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RE-RUN"))).waitUntil(Condition.visible, 5000L)
                .click();
    }

    public void processHold() {
        $(By.xpath(String.format(XPATH_PNID_BUTTON, "PROCESS HOLD"))).click();
    }

    // Need to club with RecipeRun method(visible and disable), currently overloading
    public void recipeRun(String status) {
        if (status.equalsIgnoreCase("disabled")) {
            recipeButton.shouldNotBe(selected);
        } else if (status.equalsIgnoreCase("enabled")) {
            recipeButton.waitUntil(visible, 50001)
                    .click();
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

        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            okButton.click();
        }
        commonWaiter(manualStartButton, appear);
        manualStartButton.click();
        commonWaiter(manualWindowPopup, appear);
        if (manualWindowPopup.getText()
                .equalsIgnoreCase("Recipe is already loaded")) {
            manualWindowPopup_Btn.click();
        }
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
        return (manualStopButton.waitUntil(Condition.visible, 50001)
                .isDisplayed());
    }

    public void validationOfRunDetails() {
        commonWaiter(manualStopButton, appear);
        String actualRunId = matchId.getText();
        Assert.assertEquals(actualRunId.toLowerCase(), this.recipe.getMachineName()
                .toLowerCase());
        String actualBatchId = batchId.getText();
        Assert.assertEquals(actualBatchId.toLowerCase(), this.recipe.getBatchId()
                .toLowerCase());
        String actualProductId = runId.getText();
        Assert.assertEquals(actualProductId.toLowerCase(), this.recipe.getRunId()
                .toLowerCase());
    }

    public void timeValidation() {
        Assert.assertTrue(timeValidate.isDisplayed());
    }

    public boolean verifyRestart() {
        return (manualStopButton.waitUntil(Condition.visible, 50001)
                .isDisplayed());
    }

    public void incrementTimer() {
        SelenideElement minuteValidate = $(By.xpath(String.format(timeDetails, 3)));
        SelenideElement secondValidate = $(By.xpath(String.format(timeDetails, 4)));
        int fristTime = Integer.parseInt(secondValidate.getText());
        int minFirstTime = Integer.parseInt(SelenideHelper.removeLastCharOptional(minuteValidate.getText()));
        Selenide.sleep(2000);
        int secondTime = Integer.parseInt(secondValidate.getText());
        int minSecondTime = Integer.parseInt(SelenideHelper.removeLastCharOptional(minuteValidate.getText()));
        int differ = (minSecondTime * 60 + secondTime) - (minFirstTime * 60 + fristTime);
        Assert.assertTrue(differ >= 2);
    }

    public void stopButton() {
        manualStopButton.click();
        closeButtonOfStop.click();
    }

    public void verifyPostRunDate() {
        String startDate1 = startDate.getText();
        Assert.assertTrue(startDate1.matches(("([0-9]{2})/([aA-zZ]{3})/([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2})")));
        postRunCommentsText.sendKeys("completed");
    }

    public void autoClosePostRun() {
        postRunWindow.waitUntil(Condition.disappear, 600001)
                .shouldNot(visible);
    }

    public void validateStartButtonNotSelect(String status) {
        SelenideHelper.commonWaiter(manualStartButton, visible);
        if (status.equalsIgnoreCase("disabled")) {
            manualStartButton.shouldNotBe(selected);
        } else if (status.equalsIgnoreCase("enabled")) {
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd:hh:mm:ss");
        String timeStart = pauseTimerValue.getText();
        Selenide.sleep(2000);
        String timeEnd = pauseTimerValue.getText();
        Date startTime = formatter.parse(timeStart);
        Date endTime = formatter.parse(timeEnd);
        long timeDifference = (endTime).getTime() - (startTime).getTime();
        Assert.assertTrue(timeDifference > 0);
    }

    public void hold() {
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
        holdSystem();
    }

    public void verifyApprovedRecipe() {
        SelenideHelper.commonWaiter(status, visible);

        for (SelenideElement element : loadRecipeStatus) {
            Assert.assertEquals("Approved-Active", element.getText());
        }
    }

    public void preRunWindow_Popup() {
        commonWaiter(preRunWindowPopop, visible);
    }

    public void okButton() {
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
        Selenide.sleep(2000);
    }

    public void validateHighlightedMsg(String message) {
        for (SelenideElement element : textBox_RedClrMsg) {
            Assert.assertEquals("Mandatory message check ", message, element.getText());
        }
    }

    public void runIdManual(String value) {
        if (manualWindowPopup.exists()) {
            Assert.assertEquals("warning pop displayed: ", "Recipe is already loaded", manualWindowPopup.getText());
            manualWindowPopup_Btn.click();
        }
        SelenideHelper.commonWaiter(runIdTextbox, visible)
                .click();
        Selenide.sleep(2000);
        runIdTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        runIdTextbox.sendKeys(value);
        Selenide.sleep(2000);
        runIdTextbox.sendKeys(Keys.ENTER);
    }

    public void uniqBatchId(String batchId) {
        SelenideHelper.commonWaiter(batchIdTextbox, visible)
                .click();
        Selenide.sleep(2000);
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        okButton.click();
    }

    public void validateMsg(String message) {
        if (commonWaiter(runIdExistMsg, visible).getText()
                .equalsIgnoreCase(message)) {
            runIdExistMsg.shouldBe(visible);
        } else {
            runIdExistMsg.shouldNotBe(visible);
        }
        Selenide.sleep(2000);
        SelenideHelper.commonWaiter(runIdTextbox, visible)
                .click();
        Selenide.sleep(2000);
        SelenideHelper.commonWaiter(runIdTextbox, visible)
                .clear();
    }

    public void manualValidation(String ManualOperationName, String runId, String batchId, String productId,
            String beforeComments) {
        // start_button();
        if (manualWindowPopup.exists()) {
            Assert.assertEquals("warning pop displayed: ", "Recipe is already loaded", manualWindowPopup.getText());
            manualWindowPopup_Btn.click();
        }
        manualOpareationTextbox.click();
        manualOpareationTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        manualOpareationTextbox.setValue(ManualOperationName);
        runIdTextbox.click();
        runIdTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        runIdTextbox.setValue(runId);
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.click();
        preRunCommentsText.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        preRunCommentsText.sendKeys(beforeComments);
    }

    public void iValidateSpecialChar_manaul(String spcialCharacter) {
        preRunCommentsText.click();
        preRunCommentsText.sendKeys(spcialCharacter);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
    }

    public void iValidateSpecialChar_run(String runId, String batchId, String productId, String value) {
        runIdTextbox.setValue(runId);
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(value);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
        specialCharactarErrorMsg.scrollIntoView(true);
        Selenide.sleep(2000);
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
        loadButton.waitUntil(Condition.visible, 20000L);
    }

    public boolean verifyRecipeDetails(String batch_Id) {
        boolean isResult = false;
        if (batchId.getText()
                .equalsIgnoreCase(batch_Id) && recipeStep.isDisplayed()) {
            isResult = true;
        }
        return isResult;
    }

    public void clickOnClose() {
        closeButton.waitUntil(Condition.visible, 5000L)
                .click();
    }

    public void mainPage() {
        SelenideHelper.commonWaiter(mainPage, visible)
                .click();
    }

    public void start_button() {
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            commonWaiter(okButton, visible).click();
        }
        if ($(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).click();
            clickYesButton.waitUntil(Condition.visible, 1000)
                    .click();
            okButton.waitUntil(Condition.visible, 5001)
                    .click();
        }

        manualOperationButton.waitUntil(visible, 50001)
                .click();
        manualOperationSelected.shouldBe(visible);
        commonWaiter(start_Btn, visible).click();

    }

    public void run_Btn() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000L);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
    }

    public void iVerifyDialogBox() {
        if (processhold_Box.isDisplayed()) {
            Assert.assertTrue("Element is Displayed", processHoldDailogBox.isDisplayed());
        }
    }

    public void validateNoBtn() {
        SelenideHelper.commonWaiter(noButton, visible)
                .click();
    }

    public void validateYesBtn() {
        SelenideHelper.commonWaiter(yesButton, visible)
                .click();
    }

    public void processRestart() {
        if (!processhold_Box.isDisplayed()) {
            SelenideHelper.commonWaiter(processRestart, visible);
        } else {
            processRestart.shouldNotBe(visible);
        }
    }

    public void iVerifyRecipeConsoleElement() {
        if (!collapseIcon.isDisplayed()) {
            SelenideHelper.commonWaiter(expandIcon, visible)
                    .click();
        }
        if (processhold_Box.isDisplayed()) {
            recipeButton.shouldNot(visible);
            manualOperations.shouldNotHave(visible);
        }

    }

    public void iSelectProcessRestart() {
        SelenideHelper.commonWaiter(processRestart, visible)
                .click();
    }

    public void iVerifyProcessRestartPopup() {
        if (processRestartWindowPopup.isDisplayed()) {
            processRestartMsg.shouldBe(visible);
            noButton.shouldBe(visible);
            yesButton.shouldBe(visible);
        }
    }

    public void iVerifyProcessRestartToProcessHold() {
        Selenide.sleep(2000);
        if (!processRestart.isDisplayed()) {
            recipeButton.shouldBe(visible);
        } else {
            recipeButton.shouldNotBe(visible);
        }
    }

    public void expandRecipeConsole() {
        SelenideHelper.commonWaiter(expandIcon, visible)
                .click();
    }

    public void iVerifyAstericMark(String Mark) {
        for (SelenideElement element : textBox_RedClrMsg) {
            Assert.assertEquals("Mandatory message check", Mark, element.getText());
        }
    }

    public void stopBtn() {
        SelenideHelper.commonWaiter(manualStopButton, visible)
                .click();
        Selenide.sleep(2000);
    }

    public String startRecipe(String productId, String batchId, String beforeComments) {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).waitUntil(Condition.visible, 20000L);
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "RUN"))).click();
        String runId = runIdTextbox.getValue();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        Selenide.sleep(2000);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);
        okButton.click();

        return runId;
    }

    public void startAndWaitManualOperation(int seconds) {
        SelenideHelper.commonWaiter(manualStopButton, visible)
                .waitUntil(Condition.visible, seconds * 3000L);
        manualStopButton.click();
        Selenide.sleep(4000);
        validateYesBtn();
    }

    public void iProvideData(String productId, String beforeComments) {
        productIdTextbox.click();
        productIdTextbox.setValue(productId);
        preRunCommentsText.setValue(beforeComments);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
    }

    public void iVerifyRecipeName() {
        stepAction.moveToElement(recipeNameTrimmed)
                .moveToElement(recipeNameTrimmed)
                .click()
                .build()
                .perform();
        Selenide.sleep(2000);
    }

    public void iVerifyRecipeNameDisplayedOrTrimmed(String condition) {
        SelenideElement recipelenthName = $(By.xpath("//label[text()='testRecipeWithChar30NameLengt']"));
        if (condition.equalsIgnoreCase("Display")) {
            SelenideHelper.commonWaiter(recipelenthName, appear);
        } else if (condition.equalsIgnoreCase("Trimmed")) {
            stepAction.moveToElement(recipelenthName)
                    .perform();
            Selenide.sleep(2000);
        }
    }

    public void iVerifyConditionalStatement() {
        stepAction.moveToElement(ConditionalStatement)
                .perform();
        Selenide.sleep(2000);
    }

    public void iCheckStepDetailsWithMouseHover() {
        SelenideElement BatchIdValue = $(By.xpath(String.format(stepIdDetails, 3)));
        stepAction.moveToElement(BatchIdValue)
                .build()
                .perform();
        Selenide.sleep(2000);
        SelenideElement RunIdValue = $(By.xpath(String.format(stepIdDetails, 4)));
        stepAction.moveToElement(RunIdValue)
                .build()
                .perform();
        Selenide.sleep(2000);
    }

    public void iDisplayedRunIdAndBatchId() {
        SelenideElement postRunBatchID = $(By.xpath(String.format(postRunDetails, 3)));
        Assert.assertTrue(postRunWindow.isDisplayed());
        Assert.assertTrue(postRunId.isDisplayed());
        Assert.assertTrue(postRunBatchID.isDisplayed());
    }

    public void iLoadRecipelink(String recipeName) {
        if (abortButton.isDisplayed()) {
            abortButton.click();
            clickYesButton.waitUntil(Condition.visible, 1000)
                    .click();
            okButton.waitUntil(Condition.visible, 5001)
                    .click();
        }

        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            okButton.click();
        }

        if (manualStartButton.isDisplayed()) {
            recipeButton.click();
        }

        if ($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
        }

        $(By.xpath(String.format(XPATH_TEXTS, "Load Recipe"))).click();
        loadButton.waitUntil(Condition.visible, 20000L);
        $(By.xpath(String.format(XPATH_LOAD_RECIPE, recipeName))).click();
        loadButton.click();
    }

    public void checkButton() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "JUMP_STEP"))).shouldBe(visible);
        Selenide.sleep(2000);
    }

    public boolean iCheckRecipeDetails(String batch_Id, String runId) {
        return recipeRunBatchId.getText()
                .equalsIgnoreCase(batch_Id)
                && recipeRunId.getText()
                        .equalsIgnoreCase(runId);
    }

    public void verifyAbortButton() {
        $(By.xpath(String.format(XPATH_CTRL_ICONS, "ABORT"))).waitUntil(Condition.visible, 50001);
    }

    public void iCheckLengthyCharacter() {
        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            okButton.click();
        }
        commonWaiter(manualStartButton, appear);
        manualStartButton.click();
        commonWaiter(manualWindowPopup, appear);
        if (manualWindowPopup.getText()
                .equalsIgnoreCase("Recipe is already loaded")) {
            manualWindowPopup_Btn.click();
        }

        this.recipe.setMachineName(RandomStringUtils.randomAlphabetic(120));
        manualOperationName.sendKeys(this.recipe.getMachineName());
        manualOperationName.sendKeys(Keys.ENTER);
    }

    public void iCheckErrorMessage(String name) {
        if (manualOperation_lengthychar.getText()
                .equalsIgnoreCase(name)) {
            manualOperation_lengthychar.shouldBe(visible);
        }

        else {
            manualOperation_lengthychar.shouldNotBe(visible);
        }
        SelenideHelper.commonWaiter(manualOperationName, visible)
                .click();
        manualOperationName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    public void iEnterLengthyChar(String ManualOperationName, String runId, String batchId, String productId) {
        manualOpareationTextbox.click();
        manualOpareationTextbox.setValue(ManualOperationName);
        runIdTextbox.setValue(runId);
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
    }

    public void iVerifyDisplayedOrTrimmed(String condition) {


        if (condition.equalsIgnoreCase("Display")) {

            SelenideHelper.commonWaiter(ManualOperationRecipe, appear);
            SelenideHelper.commonWaiter(RunID, appear);
            SelenideHelper.commonWaiter(BatchID, appear);

        } else if (condition.equalsIgnoreCase("Trimmed")) {

            stepAction.moveToElement(ManualOperationRecipe)
                    .perform();
            Selenide.sleep(2000);
            stepAction.moveToElement(RunID)
                    .perform();
            Selenide.sleep(2000);
            stepAction.moveToElement(BatchID)
                    .perform();
            Selenide.sleep(2000);
        }
    }

    public void iVerifyPostRunWindowValues(String condition) {
        SelenideElement ManualOperationRecipe = $(By.xpath(String.format(postRunDetails, "1")));
        SelenideElement RunID = $(By.xpath(String.format(postRunDetails, "2")));
        SelenideElement ProductID = $(By.xpath(String.format(postRunDetails, "3")));
        SelenideElement BatchID = $(By.xpath(String.format(postRunDetails, "4")));

        if (condition.equalsIgnoreCase("Display")) {
            SelenideHelper.commonWaiter(ManualOperationRecipe, appear);
            SelenideHelper.commonWaiter(RunID, appear);
            SelenideHelper.commonWaiter(ProductID, appear);
            SelenideHelper.commonWaiter(BatchID, appear);

        } else if (condition.equalsIgnoreCase("Trimmed")) {
            stepAction.moveToElement(ManualOperationRecipe)
                    .perform();
            stepAction.moveToElement(RunID)
                    .perform();
            stepAction.moveToElement(ProductID)
                    .perform();
            stepAction.moveToElement(BatchID)
                    .perform();
        }
    }

    public void collapseRecipeConsoleNotDisplay() {
        SelenideHelper.commonWaiter(collapseIcon, disappears);
    }

    public void gotoManualOperations() {
        SelenideHelper.commonWaiter(manualOperations, visible)
                .click();
    }

    public void expandConsole() {
        if (!collapseIcon.isDisplayed()) {
            SelenideHelper.commonWaiter(expandIcon, visible)
                    .click();
        }
    }

    public void iClickOnAbortButton() {
        abortButton.waitUntil(visible, 5000L)
                .click();
        clickYesButton.waitUntil(Condition.visible, 1000L)
                .click();
    }

    public void stopManualRunAfterSecond(int second) {
        manualStopButton.waitUntil(Condition.visible, second * 1001L);
        closeButtonOfStop.click();
        postRunWindow.waitUntil(Condition.disappear, 1000)
                .shouldNot(visible);
    }

    public void iVerifyConsoleDetails() {
        commonWaiter(holdButton, appear);
        commonWaiter(recipeButton, appear);
        commonWaiter(manualOperations, appear);
    }

    public void iVerifySpecialCharcterMsg() {
        commonWaiter(okButton, visible).click();
        specialCharactarErrorMsg.scrollIntoView(true);
        commonWaiter(specialCharactarErrorMsg, appear);
    }

    public void openLoadRecipePage() {
        if (restartButton.isDisplayed()) {
            restartSystem();
            SelenideHelper.commonWaiter(holdButton, visible);
        }
        if (abortIcon.isDisplayed()) {
            abortIcon.click();
            clickYesButton.waitUntil(Condition.visible, 1000)
                    .click();
            okButton.waitUntil(Condition.visible, 5001)
                    .click();
        }
        if ($(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).isDisplayed()) {
            $(By.xpath(String.format(XPATH_TEXTS, "Clear Panel"))).click();
        }
        if (manualStopButton.isDisplayed()) {
            manualStopButton.click();
            closeButtonOfStop.click();
            commonWaiter(okButton, visible).click();
        }
        if (manualStartButton.isDisplayed()) {
            recipeButton.click();
        }
        $(By.xpath(String.format(XPATH_TEXTS, "Load Recipe"))).click();
    }

    public void verifyTooltipLoadRecipePage() {

        // Check the values for RecipeName Column
        for (int i = 1; i <= recipeListTable.size(); i++) {
            Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i, 1))).getAttribute("title")
                    .matches("^[a-zA-Z0-9]*$"));
        }
        // Check the values for status Column
        for (int i = 1; i < recipeListTable.size(); i++) {
            Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i, 2))).getAttribute("title")
                    .equals("Approved-Active"));
        }
        // Check the values for date Column
        for (int i = 1; i < recipeListTable.size(); i++) {
            Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i, 3))).getAttribute("title")
                    .matches(("([a-zA-Z0-9]{3}) ([0-9]{2}),([0-9]{4})")));
        }
        // Check the values for created By Column
        for (int i = 1; i < recipeListTable.size(); i++) {
            Assert.assertTrue($(By.xpath(String.format(recipeListTableValues, i, 4))).getAttribute("title")
                    .equals("Bio4CAdmin"));
        }

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
        okButton.waitUntil(enabled, 30000);
        commonWaiter(okButton, visible).click();
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

    public void startRecipeWithErrors(String productId, String batchId, String beforeComments) {
        runIcon.waitUntil(Condition.visible, 20000l);
        runIcon.click();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(beforeComments);
        preRunCommentsText.sendKeys(Keys.TAB);
    }

    public void verifyCommentErrorMessage(String message) {
        $(By.xpath("//label[text()='Comments']")).click();
        $(By.xpath("(//span[@class='formInputTexterrorStyle'])[4]")).scrollIntoView(false);
        Assert.assertTrue((commonWaiter(runCommentErrorMsg, visible)).getText()
                .equalsIgnoreCase(message));
        $(By.xpath("//div[@id='customized-dialog-title']/img")).click();
    }

    public void seeSystemRestarted() {
        SelenideHelper.commonWaiter(holdButton, visible);
    }

    public String seeRestartOrHoldContent() {
        return commonWaiter($(By.xpath(XPATH_RESTART_OR_HOLD_BUTTON)), visible).text();
    }

    public void manualOperationTab(String value) {
        if (value.equalsIgnoreCase("enabled")) {
            manualOperationButton.waitUntil(visible, 50001)
                    .click();
            manualOperationSelected.shouldBe(visible);
        } else if (value.equalsIgnoreCase("disabled")) {
            commonWaiter(manualOperationButton, visible);
            manualOperationButton.shouldNotBe(selected);
        }
    }

    public void runButton(String status) {
        runIcon.waitUntil(visible, 10000);
        if (status.equalsIgnoreCase("disabled")) {
            runIcon.shouldNotBe(selected);
        } else if (status.equalsIgnoreCase("enabled")) {
            runIcon.shouldBe(visible);
        }
    }

    public void preRunWindowNotVisible() {
        preRunWindowPopop.shouldNotBe(visible);
    }

    public void validateCancelBtn() {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_PNID_BUTTON, "Cancel"))), visible)
                .click();
    }

    public void validateConfirmBtn() {
        SelenideHelper.commonWaiter(processRestartMsg, visible)
                .click();
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_PNID_BUTTON, "Confirm"))), visible)
                .click();
    }

    public void iValidateSpecialCharManual(String ManualOperationName, String runId, String batchId, String productId,
            String value) {
        manualOpareationTextbox.click();
        manualOpareationTextbox.setValue(ManualOperationName);
        runIdTextbox.setValue(runId);
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(value);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();

    }

    public void checkJunkWords() {
        Selenide.sleep(1000);
        $(By.xpath("//p[contains(text(),'IS')]")).shouldNotBe(visible);
    }

    public void verifyRecipeRunComplete() {
        postRunWindow.waitUntil(Condition.appear, 100001)
                .shouldBe(visible);
        SelenideHelper.commonWaiter(preRunCommentsText, visible)
                .sendKeys("Ok");
        okButton.waitUntil(Condition.visible, 5000l)
                .click();
    }

    public void ifElseStepExecuted(String action) {
        commonWaiter($(By.xpath(String.format("//label[contains(text(),'%s')]", action))), visible);
    }

    public void iValidateSpecialCharRun(String runId, String batchId, String productId, String value) {
        runIdTextbox.setValue(runId);
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunCommentsText.sendKeys(value);
        SelenideHelper.commonWaiter(okButton, visible)
                .click();
    }


}
