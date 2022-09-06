package pageobjects.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.enabled;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RecipeConsolePage {
    private final String XPATH_PNID_BUTTON = "//span[contains(text(),'%s')]";
    private final String XPATH_LOAD_RECIPE = "//*[@title='%s']";
    private final String XPATH_RECIPE_LOADED_BEFORE = "//*[@id='trimString' and contains(@title,'%s') and @title!='%s']";


    private final SelenideElement loadRecipeText = $(By.xpath("//p[contains(text(),'Load Recipe')]"));
    private final SelenideElement clearRecipeText = $(By.xpath("//p[contains(text(),'Clear Panel')]"));
    private final SelenideElement preRunCommentsText = $(By.xpath("//textarea[@name='comment']"));
    private final SelenideElement executionStatusText = $(By.id("runStatus_Id"));

    private final SelenideElement expandIcon = $(By.xpath("//img[contains(@class,'jss')]"));
    private final SelenideElement runIcon = $(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement abortIcon = $(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement rerunIcon = $(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement pauseIcon = $(By.xpath("//img[contains(@src,'Group')]"));
    private final SelenideElement resumeIcon = $(By.xpath("//img[contains(@src,'RESUME')]"));
    private final SelenideElement jumpStepIcon = $(By.xpath("//img[contains(@src,'JUMP_STEP')]"));
    private final SelenideElement inputStepNumber = $(By.xpath("//input[@id='standard-number']"));

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

    public void holdAndRestart() {
        if (restartButton.isDisplayed()) {
            restartSystem();
        }
        holdSystem();
        restartSystem();
    }

    private void restartSystem() {
        SelenideHelper.commonWaiter(restartButton, visible).click();
        SelenideHelper.commonWaiter(reEstablishStateButton, enabled).click();
        SelenideHelper.commonWaiter(confirmButton, visible).click();
    }

    private void holdSystem() {
        SelenideHelper.commonWaiter(holdButton, visible).click();
        SelenideHelper.commonWaiter(yesButton, visible).click();
    }

    public void gotoRecipeConsole() {
        expandIcon.click();
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

    public String startAndWaitRecipe(String productId, String batchId, String beforeComments, String afterComments, int seconds) {

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
        preRunCommentsText.sendKeys(afterComments);
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
        rerunIcon.waitUntil(Condition.visible, 5000l);
    }

    public void clickPauseButton() {
        pauseIcon.click();
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
}
