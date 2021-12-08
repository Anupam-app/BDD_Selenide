package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import static com.codeborne.selenide.Condition.visible;
import dataobjects.RecipeAction;

public class RecipePage {

    private final SelenideElement recipePageLinkText = $(By.id("RecipeManagement"));
    private final SelenideElement editorLinkText=$(By.xpath("//a[text()='Editor']"));
    private final SelenideElement browserLinkText=$(By.xpath("//a[text()='Browser']"));

    private final SelenideElement recipeElementText =$(By.xpath("//div[@class='recipeTabs']"));
    private final SelenideElement loadRecipeText=$(By.xpath("//p[text()='Load Recipe']"));
    private final SelenideElement clearRecipeText=$(By.xpath("//p[text()='Clear Panel']"));
    private final SelenideElement preRunComments=$(By.xpath("//textarea[@name='comment']"));

    private final SelenideElement recipeSearchTextBox = $(By.id("search"));
    private final SelenideElement phaseElementTextBox =$(By.className("phase-Name"));
    private final SelenideElement productIdTextbox=$(By.xpath("//input[@name='productId']"));
    private final SelenideElement batchIdTextbox=$(By.xpath("(//*[contains(@class,'autocompletediv')]//input)[1]"));

    private final SelenideElement openButton=$(By.className("open-recipe-btn"));
    private final SelenideElement plusButton=$(By.className("icon-plus"));
    private final By deletePhaseButton = By.className("deleteButton");
    private final SelenideElement primaryButton=$(By.className("btn-primary"));
    private final SelenideElement loadButton=$(By.xpath("//span[contains(text(),'Load')]"));
    private final SelenideElement okButton=$(By.xpath("//button[contains(text(),'OK')]"));
    private final SelenideElement saveButton=$(By.xpath("//button[contains(text(),'Save')]"));
    
    private final String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";

    private final By phasesList = By.className("phaseHead");
    private final SelenideElement phasesListClick =$(By.className("phaseHead"));
    private final SelenideElement phasesListClick1 =$(By.xpath("(//label[@class='phaseHead'])[1]"));
    private final SelenideElement phasesListClick2 =$(By.xpath("((//label[@class='phaseHead'])[1]//following::span[@class='deleteBtn egu-right-block']/input)[1]"));

    private final SelenideElement statusDraft=$(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectInReview=$(By.xpath("//span[text()='In-Review']"));
    private final SelenideElement statusInReview=$(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectApprove=$(By.xpath("//span[text()='Approved-Active']"));
    private final SelenideElement inputPassword=$(By.xpath("//input[@type='password']"));
    private final SelenideElement statusApproved=$(By.xpath("//div[@class='status-tooltip']"));

    private final SelenideElement expandIcon=$(By.xpath("//img[@class='jss9']"));
    private final SelenideElement runIcon=$(By.xpath("//img[contains(@src,'RUN')]"));
    private final SelenideElement abortIcon=$(By.xpath("//img[contains(@src,'ABORT')]"));
    private final SelenideElement rerunIcon=$(By.xpath("//img[contains(@src,'RE-RUN')]"));
    private final SelenideElement pauseIcon=$(By.xpath("//img[contains(@src,'PAUSE')]"));
    private final SelenideElement resumeIcon=$(By.xpath("//img[contains(@src,'RESUME')]"));
    private final SelenideElement jumpStepIcon=$(By.xpath("//img[contains(@src,'JUMP_STEP')]"));
    private final SelenideElement inputStepNumber=$(By.xpath("//input[@id='standard-number']"));
    private final SelenideElement okStepButton=$(By.xpath("//span[contains(text(),'OK')]"));
    private final SelenideElement clickYes=$(By.xpath("//span[text()='Yes']"));
    private final SelenideElement executionStatus = $(By.id("runStatus_Id"));
    private final SelenideElement importButton=$(By.xpath("//button[contains(text(),'Import')]"));
    private String xpathEditExportIcon = "//tr[td[contains(.,'%s')]]/td/div[contains(@class, 'export-icon')]";
    private final String NOTIFICATION_TEXT="Recipe %s Success";
    private SelenideElement saveText = $(By.className("notification-title"));
    private final String XPATH_LOAD_RECIPE ="//*[@title='%s']";
    private String xpathImportRecipe ="//tr[td[contains(.,'%s')]]";
    public void goTo() {
        recipePageLinkText.click();
    }

    public void setSearch(String recipeName) {
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
    }

    public void goToEditMode() {
        editorLinkText.waitUntil(Condition.visible,5000l);
        editorLinkText.click();
    }

    public void goToBrowserMode() {
        browserLinkText.click();
    }

    public void edit(String recipeName) {
        commonWaiter($(By.xpath(String.format(xpathEditPage, recipeName))), Condition.visible) .click();
        commonWaiter(openButton, Condition.visible) .click();
    }

    public void deleteAllPhases() {
        Selenide.$$(phasesList).forEach(e->{
            e.click();
            e.find(deletePhaseButton).click();
        });
    }

    public void addPhase(String phase) {
        plusButton.waitUntil(Condition.visible,5000l);
        plusButton.click();
        SelenideElement searchTextBox=$(By.className("search-txt-box"));
        searchTextBox.sendKeys("start");
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL+"g");
        phaseElementTextBox.sendKeys(phase);
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void saveRecipe(String recipeName) {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
        SelenideElement recipeInputSave=$(By.className("selected-recipe-input"));
        $(By.className("selected-recipe-input")).clear();
        recipeInputSave.setValue(recipeName);
        $(By.className("btn_primary")).click();
    }

    public void saveModifiedRecipe() {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Save']")).click();
    }

    public String getPhaseName() {
        return phaseElementTextBox.getValue();
    }

    public String getRecipeName() {
        return recipeElementText.getText();
    }

    public void naviagateToEditorTab() {
        recipePageLinkText.click();
        switchTo().frame("CrossDomainiframeId");
        editorLinkText.waitUntil(Condition.visible,5000l);
        editorLinkText.click();
    }

    public void createRecipe(String recipenode) {
        plusButton.waitUntil(Condition.visible,5000l);
        plusButton.click();
        SelenideElement searchTextBox=$(By.className("search-txt-box"));
        searchTextBox.sendKeys(recipenode);
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL+"g");
        phaseElementTextBox.sendKeys(RandomStringUtils.randomAlphabetic(10));
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void verifyRecipe(String recipeName) {
        browserLinkText.click();
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible,5000l);
        openButton.click();
    }

    public void editRecipe(String recipeName) {
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible,5000l);
        openButton.click();
    }

    public void deletePhaseToRecipe() {
        phasesListClick.waitUntil(Condition.visible,5000l);
        phasesListClick1.click();
        phasesListClick2.click();
        $(By.xpath("//button[text()='Ok']")).click();
    }

    public void approveRecipe(String password) {
        statusDraft.click();
        selectInReview.click();
        primaryButton.click();
        $(By.xpath("//button[text()='Ok']")).click();
        statusInReview.click();
        selectApprove.click();
        $(By.xpath("//button[text()='Change']")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[text()='SIGN']")).click();
        statusApproved.waitUntil(Condition.visible,5000l);
        browserLinkText.click();
        editorLinkText.click();

    }

    public String getStatus() {
        return statusApproved.getText();
    }

    public void gotoRecipeConsole() {
        expandIcon.click();
    }

    public void loadRecipe(String recipeName) {
        if(clearRecipeText.isDisplayed()){
            clearRecipeText.click();
        }
        loadRecipeText.click();
        loadButton.waitUntil(Condition.visible,20000l);
        $(By.xpath(String.format(XPATH_LOAD_RECIPE, recipeName))).click();
        loadButton.click();

    }

    public void recipeExecution(String productId,String batchId,String beforeComments,String afterComments) {
        runIcon.waitUntil(Condition.visible,20000l);
        runIcon.click();
        productIdTextbox.setValue(productId);
        batchIdTextbox.click();
        batchIdTextbox.sendKeys(batchId);
        batchIdTextbox.sendKeys(Keys.ENTER);
        preRunComments.sendKeys(beforeComments);
        okButton.click();
        abortIcon.waitUntil(Condition.visible,5000l);
        abortIcon.waitUntil(Condition.not(Condition.visible),30000l);
        preRunComments.sendKeys(afterComments);
        okButton.click();
    }

    public void isExecuted() {
        rerunIcon.waitUntil(Condition.visible,5000l);
    }
    
    public void clickPauseButton() {
    	pauseIcon.click();
    }
    
    public void clickResumeButton() {
    	resumeIcon.click();
    }
    
    public void clickOnJumpToStep(String stepNumber) {
    	jumpStepIcon.click();
    	inputStepNumber.setValue(stepNumber);
    	okStepButton.click();
    	
    }
    
    public void clickOnAbortButton() {
    	abortIcon.click();
    	clickYes.click();
    	okButton.click();
    }
    
    public String getExecutionStatus() {
    	return executionStatus.getText();
    }
    
    public void exportRecipe(String recipeName) {
    	$(By.xpath(String.format(xpathEditExportIcon, recipeName))).waitUntil(visible,5000l).click();
    	commonWaiter(openButton, Condition.visible) .click();
    }
    
    public void notificationMessage(RecipeAction recipeAction) {

        String expectedNotificationText="";
        switch (recipeAction){
            case EXPORT:
                expectedNotificationText=String.format(NOTIFICATION_TEXT, "Export");
                break;
            case IMPORT:
            	expectedNotificationText=String.format(NOTIFICATION_TEXT, "Import");
                break;
            	
        }

        saveText.shouldHave(Condition.text(expectedNotificationText));
    }
    
    public void importRecipe(String recipeName) {
        $(By.xpath("//*[@class=\"navButton\"][text()='File']")).click();
        $(By.xpath("//*[@class=\"submenu-value-left\"]/label[text()='Import']")).click();
        $(By.xpath(String.format(xpathImportRecipe, recipeName))).click();
        importButton.click();
        SelenideElement recipeInputSave=$(By.className("rename-recipe-import-input"));
        $(By.className("rename-recipe-import-input")).clear();
        recipeInputSave.setValue(RandomStringUtils.randomAlphabetic(10));
        saveButton.click();
    }

}

