package pageobjects.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import cucumber.util.I18nUtils;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects.utility.SelenideHelper;
import static pageobjects.utility.SelenideHelper.commonWaiter;
import pageobjects.utility.SortHelper;

public class RecipePage {
    private final String XPATH_STEP = "//*[contains(@data-contextmenu,'step%s')]";

    private final String XPATH_IMPORT_RECIPE = "//tr[td[contains(.,'%s')]]";
    private final String XPATH_EDIT_EXPORT_ICON = "//tr[td[contains(text(),'%s')]]/td/div[contains(@class, 'export-icon')]";
    private final String XPATH_ORDER_ICON = "//span[@class='%s']";

    private final String XPATH_RECIPE_COLUMN_HEADER = "//th[contains(text(),'%s')]";
    private final String XPATH_RECIPE_TABLE = "//table[@id='recipeListTable']";

    private final String XPATH_RECIPE_COLUMNS = "//table[@id='recipeListTable']//td";
    private final String XPATH_RECIPE_COLUMNS_BY_INDEX = XPATH_RECIPE_COLUMNS + "[%s]";
    private final String XPATH_RECIPE_COLUMNS_BY_TEXT = XPATH_RECIPE_COLUMNS + "[contains(text(),'%s')]";

    private final String XPATH_RECIPE_OPTIONS_TEXT = "//option[@value='%s']/ancestor::li";

    private final String NOTIFICATION_TEXT_IMPORT = "The recipe %s was successfully imported!";
    private final String NOTIFICATION_TEXT_EXPORT = "The recipe %s was successfully exported!";

    private final SelenideElement recipePageLinkText = $(By.id("RecipeManagement"));
    private final SelenideElement userProfileIcon = $(By.xpath("//*[@id='userProfile']"));
    private final SelenideElement editorLinkText = $(By.xpath("//a[contains(text(),'Editor')]"));
    private final SelenideElement browserLinkText = $(By.xpath("//a[contains(text(),'Browser')]"));

    private final SelenideElement recipeElementText = $(By.xpath("//div[@class='recipeTabs']"));
    private final SelenideElement notificationText = $(By.className("notification-summary"));
    private final SelenideElement headerText = $(By.xpath("//div[@class='navWrapper']//h2"));
    private final ElementsCollection deviceShapeElements = $$(By.xpath("//div[@class='search-node']//span"));

    private final SelenideElement recipeCriteriaSearchTextBox = $(By.className("search-txt-box"));
    private final SelenideElement recipeSearchTextBox = $(By.id("search"));
    private final SelenideElement phaseElementTextBox = $(By.className("phase-Name"));
    
    private final SelenideElement openButton = $(By.className("open-recipe-btn"));
    private final SelenideElement plusButton = $(By.className("icon-plus"));
    private final SelenideElement addStepButton = $(By.xpath("//*[contains(@class, 'add-step-button')]"));
    private final By deletePhaseButton = By.className("deleteButton");
    private final SelenideElement primaryButton = $(By.className("btn-primary"));
    private final SelenideElement saveButton = $(By.xpath("//button[contains(text(),'Save')]"));
    private final SelenideElement okButton = $(By.xpath("//button[contains(text(),'Ok')]"));
    private final SelenideElement deleteButton = $(By.xpath("//*[contains(@class, 'delete-step-button')]"));

    private final SelenideElement XPATH_WARNNOTIFICATION_TEXT = $(By.xpath("//*[@class='editor-dialog']/div/div[1]"));

    private final String xpathEditPage = "//*[@id=\"recipeListTable\"]/tbody/tr/td[contains(.,'%s')]";

    private final By phasesList = By.className("phaseHead");

    private final SelenideElement statusDraft = $(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectInReview = $(By.xpath("//span[text()='In-Review']"));
    private final SelenideElement statusInReview = $(By.xpath("//div[@class='status-tooltip']"));
    private final SelenideElement selectApprove = $(By.xpath("//span[text()='Approved-Active']"));
    private final SelenideElement inputPassword = $(By.xpath("//input[@type='password']"));
    private final SelenideElement statusApproved = $(By.xpath("//div[@class='status-tooltip']"));

    private final SelenideElement clickOnDropdown = $(By.xpath("//span[@class='icon-down-arrow']"));
    private final ElementsCollection notificationTexts = $$(By.xpath("//div[@class='description-text-blue orch-notification-description']"));

    private SelenideElement filterIcon = $(By.xpath("//div[@class='filter-icon']"));
    private SelenideElement upIcon = $(By.xpath("(//div[@class='up-icon'])[1]"));

    private SelenideElement applyFilterButton = $(By.xpath("//span[text()='Apply Filters']"));

    private final ElementsCollection deleteButtons = $$(By.xpath("//*[@class='deleteButton']"));
    private final SelenideElement saveEditorButton = $(By.xpath("//button[contains(@class,'save-button')]"));
    private final SelenideElement importMenuButton = $(By.xpath("//button[contains(@class,'import-button')]"));
    private final SelenideElement importButton = $(By.xpath("//button[contains(@class,'import-button-text')]"));

    Function<Integer, List<String>> getRecipeColumns = (index) -> $$(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_INDEX, index))).texts();

    public void goTo() {
        recipePageLinkText.click();
    }

    public void setSearch(String recipeName) {
        commonWaiter(recipeSearchTextBox, visible);
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
    }

    public void goToEditMode() {
        editorLinkText.waitUntil(Condition.visible, 5000l);
        editorLinkText.click();
    }

    public void goToBrowserMode() {
        browserLinkText.click();
    }

    public void edit(String recipeName) {
        commonWaiter($(By.xpath(String.format(xpathEditPage, recipeName))), Condition.visible).click();
        commonWaiter(openButton, Condition.visible).click();
    }

    public void deleteAllPhases() {
        $$(phasesList).forEach(e -> {
            e.click();
            e.find(deletePhaseButton).click();
        });
    }

    public void addPhase(String phase) {
        addStepButton.click();
        SelenideElement searchTextBox = $(By.className("search-txt-box"));
        searchTextBox.sendKeys("setpoint");
        searchTextBox.sendKeys(Keys.ENTER);
        searchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
        phaseElementTextBox.sendKeys(phase);
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void saveRecipe(String recipeName) {
        saveEditorButton.click();
        SelenideElement recipeInputSave = $(By.className("selected-recipe-input"));
        $(By.className("selected-recipe-input")).clear();
        recipeInputSave.setValue(recipeName);
        $(By.className("btn_primary")).click();
    }
    
    public void isGeneratedNotificationWhenCreateExistingRecipe(String message) {   	
        commonWaiter(XPATH_WARNNOTIFICATION_TEXT,visible);
        XPATH_WARNNOTIFICATION_TEXT.shouldHave(text(message));
        $(By.xpath("//*[@class='btn-primary']")).click();         
    }

    public void saveModifiedRecipe() {
        saveEditorButton.click();
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
        editorLinkText.waitUntil(Condition.visible, 5000l);
        editorLinkText.click();
    }

    public void createRecipe(String recipenode) {
        plusButton.waitUntil(Condition.visible, 5000l);
        plusButton.click();
        recipeCriteriaSearchTextBox.sendKeys(recipenode);
        recipeCriteriaSearchTextBox.sendKeys(Keys.ENTER);
        recipeCriteriaSearchTextBox.sendKeys(Keys.LEFT_CONTROL + "g");
        phaseElementTextBox.sendKeys(RandomStringUtils.randomAlphabetic(10));
        phaseElementTextBox.sendKeys(Keys.ENTER);
    }

    public void verifyRecipe(String recipeName) {
        browserLinkText.click();
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible, 5000l);
        openButton.click();
    }

    public void editRecipe(String recipeName) {
        recipeSearchTextBox.setValue(recipeName);
        recipeSearchTextBox.sendKeys(Keys.ENTER);
        $(By.xpath(String.format(xpathEditPage, recipeName))).click();
        openButton.waitUntil(Condition.visible, 5000l);
        openButton.click();
    }

    public void deletePhaseToRecipe() {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_STEP, 5))), visible).click();
        deleteButton.click();
    }

    public void approveRecipe(String password) {
        statusDraft.click();
        selectInReview.click();
        primaryButton.click();
        okButton.click();
        statusInReview.click();
        selectApprove.click();
        $(By.xpath("//button[contains(text(),'Change')]")).click();
        inputPassword.sendKeys(password);
        $(By.xpath("//button[contains(text(),'SIGN')]")).click();
        statusApproved.waitUntil(Condition.visible, 5000l);
        browserLinkText.click();
        editorLinkText.click();
    }

    public String getStatus() {
        return statusApproved.getText();
    }


    public void exportRecipe(String recipeName) {
        $(By.xpath(String.format(XPATH_EDIT_EXPORT_ICON, recipeName))).waitUntil(visible, 5000l).click();
    }

    public void notificationMessageImport(String recipeName) {
        String expectedNotificationText = String.format(NOTIFICATION_TEXT_IMPORT, recipeName);
        checkNotification(expectedNotificationText);
    }

    public void checkNotification(String notification) {
        notificationTexts.shouldHave(
                CollectionCondition.anyMatch("User notification should contain this notification"
                        , n -> n.getText().equals(notification)));
    }

    public void notificationMessageExport(String recipeName) {
        String expectedNotificationText = String.format(NOTIFICATION_TEXT_EXPORT, recipeName);
        checkNotification(expectedNotificationText);
    }

    public void importRecipe(String recipeName) {
        importMenuButton.click();
        var importRecipe = $(By.xpath(String.format("//td[@title='%s']", recipeName)));
        importRecipe.click();
        importButton.click();
        saveRecipe(RandomStringUtils.randomAlphabetic(10));
        browserLinkText.waitUntil(Condition.visible, 5000l).click();
    }

    public void lookAtTheUserNotification() {
        switchTo().defaultContent();
        userProfileIcon.waitUntil(Condition.visible, 5000l).click();
    }

    public String getGeneratedName() {
        switchTo().parentFrame();
        notificationText.waitUntil(visible, 30000l, 500l);
        var notif = notificationText.text();
        var recipeName = notif.split("The recipe ")[1].split(" ")[0];
        return recipeName;
    }

    public void checkTableContainsRecipe(String recipeName) {
        SelenideHelper.commonWaiter($(By.xpath(String.format(XPATH_RECIPE_COLUMNS_BY_TEXT, recipeName))), visible);
    }

    public void selectRecipeStatus(String recipeStatus) {
        commonWaiter(filterIcon, visible);
        filterIcon.click();
        commonWaiter(upIcon, visible);
        $(By.xpath(String.format("//span[contains(text(),'%s')]", recipeStatus))).click();
        applyFilterButton.click();
    }

    public void selectCreatedBy(String user) {
        SelenideHelper.commonWaiter(clickOnDropdown, visible).click();
        commonWaiter($(By.xpath(String.format(XPATH_RECIPE_OPTIONS_TEXT, user))), visible).click();
    }

    public SelenideElement getRecipeColumnHeader(String columnName) {
        return $(By.xpath(String.format(XPATH_RECIPE_COLUMN_HEADER, columnName)));
    }

    public List<String> getAllRecipeColumnHeaders() {
        return $$(By.xpath(XPATH_RECIPE_TABLE + "//th")).texts();
    }

    public void sortList(String columnName, boolean descending) {
        SelenideElement sortAction = getRecipeColumnHeader(columnName);
        var ascendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order")));
        var descendingIcon = $(By.xpath(String.format(XPATH_ORDER_ICON, "react-bootstrap-table-sort-order dropup")));
        SortHelper.sortList(sortAction, ascendingIcon, descendingIcon, descending);
    }

    public void checkSortedElement(String columnName, boolean descending) {
        SortHelper.checkSortedElement(getAllRecipeColumnHeaders(), columnName, descending, getRecipeColumns);
    }

    public void seeContent(String expectedText) {
        headerText.shouldHave(text(expectedText));
    }

    public List<String> getDeviceShapeElementNotLoaded() {
        plusButton.waitUntil(Condition.visible, 5000l);
        plusButton.click();
        recipeCriteriaSearchTextBox.click();

        var elementNotTranslated = I18nUtils.getElementsNotI18N(deviceShapeElements);

        deleteButtons.forEach(deleteButton->deleteButton.click());

        return elementNotTranslated;
    }
}